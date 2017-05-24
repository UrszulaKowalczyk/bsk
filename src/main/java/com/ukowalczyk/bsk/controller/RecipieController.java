package com.ukowalczyk.bsk.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ukowalczyk.bsk.initializer.DatabaseInitializer;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.service.RecipieService;
import com.ukowalczyk.bsk.service.UserService;

@Controller
public class RecipieController extends AbstractController {

	@Autowired
	private UserService userService;

	@Autowired
	private RecipieService recipieService;

	@Autowired
	private DefaultController defaultController;

	@RequestMapping(value = "/recipie")
	public String user(Model model, Principal principal) {
		model.addAttribute("shownTable", "recipie");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/addRecipie", method = RequestMethod.POST)
	public String createRecipie(HttpServletRequest req, HttpServletResponse res, Model model, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_RECIPIE)) {
			model.addAttribute("shownTable", "recipie");
			return defaultController.showTable(model, principal);
		}

		String[] title = req.getParameterValues("title");
		String[] description = req.getParameterValues("description");

		Recipie recipie = new Recipie(title[0], description[0]);
		recipieService.save(recipie);

		model.addAttribute("shownTable", "recipie");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/updateRecipie", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String updateRecipie(@RequestBody Recipie updatedRecipie, Principal principal, Model model) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_RECIPIE)) {
			model.addAttribute("shownTable", "recipie");
			return defaultController.showTable(model, principal);
		}

		recipieService.save(updatedRecipie);

		model.addAttribute("shownTable", "recipie");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/deleteRecipie", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deleteRecipie(@RequestBody Recipie recipie, Principal principal, Model model) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_RECIPIE)) {
			model.addAttribute("shownTable", "recipie");
			return defaultController.showTable(model, principal);
		}

		recipieService.deleteById(recipie.getId());

		model.addAttribute("shownTable", "recipie");
		return defaultController.showTable(model, principal);
	}

}
