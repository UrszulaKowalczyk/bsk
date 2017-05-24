package com.ukowalczyk.bsk.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ukowalczyk.bsk.initializer.DatabaseInitializer;
import com.ukowalczyk.bsk.model.RecipieIngredient;
import com.ukowalczyk.bsk.service.RecipieIngredientService;
import com.ukowalczyk.bsk.service.UserService;

@Controller
public class RecipieIngredientController extends AbstractController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RecipieIngredientService recipieIngredientService;
	
	@Autowired
	private DefaultController defaultController;
	
	@RequestMapping(value = "/recipieIngredient")
	public String user(Model model, Principal principal){
		model.addAttribute("shownTable", "recipieIngredient");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/addRecipieIngredient", method = RequestMethod.POST)
	public String createRecipieIngredient(HttpServletRequest req, HttpServletResponse res, Model model, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_RECIPIE_INGREDIENT))
			return "notAllowed";

		String[] recipieId = req.getParameterValues("recipieId");
		String[] ingredientId = req.getParameterValues("ingredientId");

		recipieIngredientService.create(recipieId[0], ingredientId[0]);

		return "redirect:/";
	}

	@RequestMapping(value = "/updateRecipieIngredient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRecipieIngredient(@RequestBody RecipieIngredient updatedRecipieIngredient, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_RECIPIE_INGREDIENT))
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

		recipieIngredientService.save(updatedRecipieIngredient);

		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteRecipieIngredient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> delete(@RequestBody RecipieIngredient recipieIngredient, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_RECIPIE_INGREDIENT))
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

		recipieIngredientService.deleteById(recipieIngredient.getId());

		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
