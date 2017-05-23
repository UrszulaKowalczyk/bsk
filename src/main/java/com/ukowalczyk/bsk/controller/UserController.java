package com.ukowalczyk.bsk.controller;

import java.security.Principal;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ukowalczyk.bsk.initializer.DatabaseInitializer;
import com.ukowalczyk.bsk.model.User;
import com.ukowalczyk.bsk.service.UserService;

@Controller
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginController loginController;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String createUser(HttpServletRequest req, HttpServletResponse res, Model model, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_USER))
			return "notAllowed";

		String[] login = req.getParameterValues("login");
		String[] password = req.getParameterValues("password");
		String[] label = req.getParameterValues("label");

		User user = new User(login[0], passwordEncoder.encode(password[0]), Integer.parseInt(label[0]));
		userService.save(user);

		return "redirect:/";
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody User updatedUser, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_USER))
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

		User oldUser = userService.findOne(updatedUser.getId());
		
		String newPassword = updatedUser.getPassword();
		if(!oldUser.getPassword().equals(newPassword)) {
			String encodedNewPassword = passwordEncoder.encode(newPassword);
			updatedUser.setPassword(encodedNewPassword);
		}
		
		userService.save(updatedUser);
		
		if(!oldUser.getLogin().equals(updatedUser.getLogin())) {
			Authentication authentication = new UsernamePasswordAuthenticationToken(updatedUser.getLogin(), updatedUser.getPassword(), Collections.emptyList());
			//Authentication result = authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> delete(@RequestBody User user, Principal principal, HttpServletRequest request,
			HttpServletResponse response) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_TABLELABEL))
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

		user = userService.findOne(user.getId());
		
		if (principal.getName().equals(user.getLogin())) {
			loginController.logoutDo(request, response);
		}

		userService.delete(user);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
