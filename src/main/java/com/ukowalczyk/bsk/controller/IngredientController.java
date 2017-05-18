package com.ukowalczyk.bsk.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ukowalczyk.bsk.initializer.DatabaseInitializer;
import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.service.IngredientService;
import com.ukowalczyk.bsk.service.UserService;

@Controller
public class IngredientController {

	@Autowired
	private UserService userService;
	@Autowired
	private IngredientService ingredientService;

	@RequestMapping(value = "/showIngredients", method = RequestMethod.GET)
	public String showIngredients(Model model, Principal principal) {
		List<Ingredient> listOfIngredients = userService.getIngredients(principal);
		if (null == listOfIngredients)
			return "notAllowed";
		model.addAttribute("ingredients", listOfIngredients);
		return "listOfIngredients";
	}

	@RequestMapping(value = "/addIngredient", method = RequestMethod.GET)
	public String showFormForIngredient(Model model, Principal principal) {
		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_INGREDIENT))
			return "notAllowed";
		return "addIngredient";
	}

	@RequestMapping(value = "/addIngredient", method = RequestMethod.POST)
	public String createIngredient(HttpServletRequest req, HttpServletResponse res, Model model, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_INGREDIENT))
			return "notAllowed";

		String[] name = req.getParameterValues("name");

		if (null != ingredientService.findByName(name[0])) {
			return "redirect:/";
		}

		Ingredient ingredient = new Ingredient(name[0]);
		ingredientService.save(ingredient);

		return "redirect:/";
	}

}
