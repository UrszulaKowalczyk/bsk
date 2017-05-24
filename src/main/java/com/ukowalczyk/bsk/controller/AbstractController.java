package com.ukowalczyk.bsk.controller;

import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AbstractController {

	@Autowired
	private DefaultController defaultController;
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, Model model, Principal principal, HttpServletRequest request) {
		String tableName = request.getRequestURL().toString();
		if(tableName.contains("recipieIngredient") || tableName.contains("RecipieIngredient"))
			model.addAttribute("shownTable", "recipieIngredient");
		else if(tableName.contains("ingredient") || tableName.contains("Ingredient"))
			model.addAttribute("shownTable", "ingredient");
		else if(tableName.contains("recipie") || tableName.contains("Recipie"))
			model.addAttribute("shownTable", "recipie");
		else if(tableName.contains("tableLabel") || tableName.contains("TableLabel"))
			model.addAttribute("shownTable", "tableLabel");
		else
			model.addAttribute("shownTable", "user");
		log.error(ex.getMessage(), ex);
		model.addAttribute("error", true);
		return defaultController.showTable(model, principal);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public String handleConstraintViolationException(ConstraintViolationException ex, Model model, Principal principal, HttpServletRequest request) {
		String tableName = request.getRequestURL().toString();
		if(tableName.contains("recipieIngredient") || tableName.contains("RecipieIngredient"))
			model.addAttribute("shownTable", "recipieIngredient");
		else if(tableName.contains("ingredient") || tableName.contains("Ingredient"))
			model.addAttribute("shownTable", "ingredient");
		else if(tableName.contains("recipie") || tableName.contains("Recipie"))
			model.addAttribute("shownTable", "recipie");
		else if(tableName.contains("tableLabel") || tableName.contains("TableLabel"))
			model.addAttribute("shownTable", "tableLabel");
		else
			model.addAttribute("shownTable", "user");
		log.error(ex.getMessage(), ex);
		model.addAttribute("invalid", true);
		return defaultController.showTable(model, principal);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public String handleNumberFormatException(NumberFormatException ex, Model model, Principal principal, HttpServletRequest request) {
		String tableName = request.getRequestURL().toString();
		if(tableName.contains("recipieIngredient") || tableName.contains("RecipieIngredient"))
			model.addAttribute("shownTable", "recipieIngredient");
		else if(tableName.contains("ingredient") || tableName.contains("Ingredient"))
			model.addAttribute("shownTable", "ingredient");
		else if(tableName.contains("recipie") || tableName.contains("Recipie"))
			model.addAttribute("shownTable", "recipie");
		else if(tableName.contains("tableLabel") || tableName.contains("TableLabel"))
			model.addAttribute("shownTable", "tableLabel");
		else
			model.addAttribute("shownTable", "user");
		log.error(ex.getMessage(), ex);
		model.addAttribute("invalid", true);
		return defaultController.showTable(model, principal);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public String handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, Model model, Principal principal, HttpServletRequest request) {
		String tableName = request.getRequestURL().toString();
		if(tableName.contains("recipieIngredient") || tableName.contains("RecipieIngredient"))
			model.addAttribute("shownTable", "recipieIngredient");
		else if(tableName.contains("ingredient") || tableName.contains("Ingredient"))
			model.addAttribute("shownTable", "ingredient");
		else if(tableName.contains("recipie") || tableName.contains("Recipie"))
			model.addAttribute("shownTable", "recipie");
		else if(tableName.contains("tableLabel") || tableName.contains("TableLabel"))
			model.addAttribute("shownTable", "tableLabel");
		else
			model.addAttribute("shownTable", "user");
		log.error(ex.getMessage(), ex);
		model.addAttribute("denied", true);
		return defaultController.showTable(model, principal);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, Model model, Principal principal, HttpServletRequest request) {
		String tableName = request.getRequestURL().toString();
		if(tableName.contains("recipieIngredient") || tableName.contains("RecipieIngredient"))
			model.addAttribute("shownTable", "recipieIngredient");
		else if(tableName.contains("ingredient") || tableName.contains("Ingredient"))
			model.addAttribute("shownTable", "ingredient");
		else if(tableName.contains("recipie") || tableName.contains("Recipie"))
			model.addAttribute("shownTable", "recipie");
		else if(tableName.contains("tableLabel") || tableName.contains("TableLabel"))
			model.addAttribute("shownTable", "tableLabel");
		else
			model.addAttribute("shownTable", "user");
		log.error(ex.getMessage(), ex);
		model.addAttribute("denied", true);
		return defaultController.showTable(model, principal);
	}
	
	

}
