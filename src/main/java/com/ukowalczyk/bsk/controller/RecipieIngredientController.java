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
	public String user(Model model, Principal principal) {
		model.addAttribute("shownTable", "recipieIngredient");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/addRecipieIngredient", method = RequestMethod.POST)
	public String createRecipieIngredient(HttpServletRequest req, HttpServletResponse res, Model model,
			Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_RECIPIE_INGREDIENT)) {
			model.addAttribute("shownTable", "recipieIngredient");
			return defaultController.showTable(model, principal);
		}

		String[] recipieId = req.getParameterValues("recipieId");
		String[] ingredientId = req.getParameterValues("ingredientId");

		recipieIngredientService.create(recipieId[0], ingredientId[0]);

		model.addAttribute("shownTable", "recipieIngredient");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/updateRecipieIngredient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String updateRecipieIngredient(@RequestBody RecipieIngredient updatedRecipieIngredient, Principal principal,
			Model model) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_RECIPIE_INGREDIENT)) {
			model.addAttribute("shownTable", "recipieIngredient");
			return defaultController.showTable(model, principal);
		}

		recipieIngredientService.save(updatedRecipieIngredient);

		model.addAttribute("shownTable", "recipieIngredient");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/deleteRecipieIngredient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String delete(@RequestBody RecipieIngredient recipieIngredient, Principal principal, Model model) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_RECIPIE_INGREDIENT)) {
			model.addAttribute("shownTable", "recipieIngredient");
			return defaultController.showTable(model, principal);
		}

		recipieIngredientService.deleteById(recipieIngredient.getId());

		model.addAttribute("shownTable", "recipieIngredient");
		return defaultController.showTable(model, principal);
	}

}
