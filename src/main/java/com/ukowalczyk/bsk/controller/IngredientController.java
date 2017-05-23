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
import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.service.IngredientService;
import com.ukowalczyk.bsk.service.UserService;

@Controller
public class IngredientController extends AbstractController {

	@Autowired
	private UserService userService;
	@Autowired
	private IngredientService ingredientService;

	@RequestMapping(value = "/addIngredient", method = RequestMethod.POST)
	public String createIngredient(HttpServletRequest req, HttpServletResponse res, Model model, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_INGREDIENT))
			return "notAllowed";

		String[] name = req.getParameterValues("name");

		Ingredient ingredient = new Ingredient(name[0]);
		ingredientService.save(ingredient);

		return "redirect:/";
	}

	@RequestMapping(value = "/updateIngredient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateIngredient(@RequestBody Ingredient updatedIngredient, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_INGREDIENT))
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

		ingredientService.save(updatedIngredient);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteIngredient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> deleteIngredient(@RequestBody Ingredient ingredient, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_INGREDIENT))
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

		ingredientService.delete(ingredient);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
