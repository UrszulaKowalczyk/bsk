package com.ukowalczyk.bsk.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.model.TableLabels;
import com.ukowalczyk.bsk.service.IngredientService;
import com.ukowalczyk.bsk.service.RecipieService;
import com.ukowalczyk.bsk.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoggedUserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RecipieService recipieService;
	@Autowired
	private IngredientService ingredientService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showTable(Model model, Principal principal) {
		List<TableLabels> listOfReadableTables = userService.getReadableTables(principal);
		List<TableLabels> listOfWriteableTables = userService.getWriteableTables(principal);
		model.addAttribute("readeableTables", listOfReadableTables);
		model.addAttribute("writeableTables", listOfWriteableTables);
		return "showTables";
	}

	@RequestMapping(value = "/showRecipies", method = RequestMethod.GET)
	public String showRecipies(Model model, Principal principal) {
		List<Recipie> listOfRecipies = userService.getRecipies(principal);
		if (null == listOfRecipies)
			return "notAllowed";
		model.addAttribute("recipies", listOfRecipies);
		return "listOfRecipies";
	}

	@RequestMapping(value = "/showIngredients", method = RequestMethod.GET)
	public String showIngredients(Model model, Principal principal) {
		List<Ingredient> listOfIngredients = userService.getIngredients(principal);
		if (null == listOfIngredients)
			return "notAllowed";
		model.addAttribute("ingredients", listOfIngredients);
		return "listOfIngredients";
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

		if (!userService.checkIfUserCanWrite(principal, "recipie"))
			return "notAllowed";

		String[] title = req.getParameterValues("title");
		String[] description = req.getParameterValues("description");
		String[] ingredients = req.getParameterValues("multiple[]");

		List<Ingredient> listOfIngredients = ingredientService.getAndAddIfNotExist(ingredients,
				userService.findByLogin(principal));

		Recipie recipie = new Recipie(title[0], description[0], listOfIngredients);
		recipieService.save(recipie);

		return "redirect:/";
	}

	@RequestMapping(value = "/addIngredient", method = RequestMethod.GET)
	public String showFormForIngredient(Model model, Principal principal) {
		if (!userService.checkIfUserCanWrite(principal, "ingredient"))
			return "notAllowed";
		return "addIngredient";
	}

	@RequestMapping(value = "/addIngredient", method = RequestMethod.POST)
	public String createIngredient(HttpServletRequest req, HttpServletResponse res, Model model, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, "ingredient"))
			return "notAllowed";

		String[] name = req.getParameterValues("name");

		if (null != ingredientService.findByName(name[0])) {
			return "redirect:/";
		}

		Ingredient ingredient = new Ingredient(name[0]);
		ingredientService.save(ingredient);

		return "redirect:/";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}

		return "redirect:/login?logout";
	}

}
