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
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.service.IngredientService;
import com.ukowalczyk.bsk.service.RecipieService;
import com.ukowalczyk.bsk.service.UserService;

@Controller
public class RecipieController {

	@Autowired
	private UserService userService;
	@Autowired
	private RecipieService recipieService;
	@Autowired
	private IngredientService ingredientService;

	@RequestMapping(value = "/showRecipies", method = RequestMethod.GET)
	public String showRecipies(Model model, Principal principal) {
		List<Recipie> listOfRecipies = userService.getRecipies(principal);
		if (null == listOfRecipies)
			return "notAllowed";
		model.addAttribute("recipies", listOfRecipies);
		return "listOfRecipies";
	}

	@RequestMapping(value = "/addRecipie", method = RequestMethod.GET)
	public String showForm(Model model, Principal principal) {
		if (!userService.checkIfUserCanWrite(principal, "recipie"))
			return "notAllowed";
		List<Ingredient> listOfIngredients = userService.getIngredients(principal);
		boolean ingredientWriteAccess = userService.checkIfUserCanWrite(principal, "ingredient");
		boolean ingredientReadAccess = userService.checkIfUserCanRead(principal, "ingredient");
		model.addAttribute("ingredients", listOfIngredients);
		model.addAttribute("ingredientWriteAccess", ingredientWriteAccess);
		model.addAttribute("ingredientReadAccess", ingredientReadAccess);
		return "addRecipie";
	}

	@RequestMapping(value = "/addRecipie", method = RequestMethod.POST)
	public String createRecipie(HttpServletRequest req, HttpServletResponse res, Model model, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_RECIPIE))
			return "notAllowed";

		String[] title = req.getParameterValues("title");
		String[] description = req.getParameterValues("description");
		String[] ingredients = req.getParameterValues("multiple[]");

		List<Ingredient> listOfIngredients = ingredientService.create(ingredients, principal);

		Recipie recipie = new Recipie(title[0], description[0], listOfIngredients);
		recipieService.save(recipie);

		return "redirect:/";
	}

}
