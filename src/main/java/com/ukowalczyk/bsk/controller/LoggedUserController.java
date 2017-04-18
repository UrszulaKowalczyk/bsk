package com.ukowalczyk.bsk.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ukowalczyk.bsk.enums.LabelEnum;
import com.ukowalczyk.bsk.model.Label;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.request.RecipieRequest;
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showRecipies(Model model, Principal principal) {
		List<Recipie> listOfRecipies = userService.getVisibleRecipies(principal);
		model.addAttribute("recipies", listOfRecipies);
		return "listOfRecipies";
	}

	@RequestMapping(value = "/addRecipie", method = RequestMethod.GET)
	public String showForm(Model model, Principal principal) {
		List<Label> listOfLabels = userService.getHigherLabels(principal);
		model.addAttribute("recipies", listOfLabels);
		return "addRecipie";
	}

	@RequestMapping(value = "/addRecipie", method = RequestMethod.POST)
	public String createRecipie(@ModelAttribute RecipieRequest recipieRequest, Model model, Principal principal) {
		log.info("to sie udalo {}", recipieRequest);
		LabelEnum enumLabel = null;
		for (LabelEnum label : LabelEnum.values()) {
			if (label.toString().equals(recipieRequest.getLabel()))
				enumLabel = label;
		}
		Label label = labelService.findByValue(enumLabel);
		Recipie recipie = new Recipie(recipieRequest.getTitle(), recipieRequest.getDescription(), label);
		recipieService.save(recipie);
		log.info("zapisac tez sie udalo");
		return "redirect:/";
	}

}
