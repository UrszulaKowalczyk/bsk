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

import com.ukowalczyk.bsk.enums.LabelEnum;
import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.model.Label;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.service.IngredientService;
import com.ukowalczyk.bsk.service.LabelService;
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
	private LabelService labelService;
	@Autowired
	private IngredientService ingredientService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showRecipies(Model model, Principal principal) {
		List<Recipie> listOfRecipies = userService.getVisibleRecipies(principal);
		model.addAttribute("recipies", listOfRecipies);
		return "listOfRecipies";
	}

	@RequestMapping(value = "/addRecipie", method = RequestMethod.GET)
	public String showForm(Model model, Principal principal) {
		List<Label> listOfLabels = userService.getHigherLabels(principal);
		List<Ingredient> listOfIngredients = userService.getVisibleIngredients(principal);
		model.addAttribute("labels", listOfLabels);
		model.addAttribute("ingredients", listOfIngredients);
		return "addRecipie";
	}

	@RequestMapping(value = "/addRecipie", method = RequestMethod.POST)
	public String createRecipie(HttpServletRequest req, HttpServletResponse res, Model model, Principal principal) {

		String[] title = req.getParameterValues("title");
		String[] labelFromReq = req.getParameterValues("label");
		String[] description = req.getParameterValues("description");
		String[] ingredients = req.getParameterValues("multiple[]");

		List<Ingredient> listOfIngredients = ingredientService.getAndAddIfNotExist(ingredients,
				userService.findByLogin(principal));

		LabelEnum enumLabel = null;
		for (LabelEnum label : LabelEnum.values()) {
			if (label.toString().equals(labelFromReq[0]))
				enumLabel = label;
		}
		Label label = labelService.findByValue(enumLabel);

		Recipie recipie = new Recipie(title[0], description[0], listOfIngredients, label);
		recipieService.save(recipie);

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
