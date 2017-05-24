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
import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.service.IngredientService;
import com.ukowalczyk.bsk.service.UserService;

@Controller
public class IngredientController extends AbstractController {

	@Autowired
	private UserService userService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private DefaultController defaultController;

	@RequestMapping(value = "/ingredient")
	public String user(Model model, Principal principal) {
		model.addAttribute("shownTable", "ingredient");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/addIngredient", method = RequestMethod.POST)
	public String createIngredient(HttpServletRequest req, HttpServletResponse res, Model model, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_INGREDIENT)) {
			model.addAttribute("shownTable", "ingredient");
			return defaultController.showTable(model, principal);
		}

		String[] name = req.getParameterValues("name");

		Ingredient ingredient = new Ingredient(name[0]);
		ingredientService.save(ingredient);

		model.addAttribute("shownTable", "ingredient");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/updateIngredient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String updateIngredient(@RequestBody Ingredient updatedIngredient, Principal principal, Model model) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_INGREDIENT)) {
			model.addAttribute("shownTable", "ingredient");
			return defaultController.showTable(model, principal);
		}

		ingredientService.save(updatedIngredient);

		model.addAttribute("shownTable", "ingredient");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/deleteIngredient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deleteIngredient(@RequestBody Ingredient ingredient, Principal principal, Model model) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_INGREDIENT)) {
			model.addAttribute("shownTable", "ingredient");
			return defaultController.showTable(model, principal);
		}

		ingredientService.deleteById(ingredient.getId());

		model.addAttribute("shownTable", "ingredient");
		return defaultController.showTable(model, principal);
	}

}
