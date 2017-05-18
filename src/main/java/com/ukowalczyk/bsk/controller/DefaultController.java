package com.ukowalczyk.bsk.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ukowalczyk.bsk.model.TableLabel;
import com.ukowalczyk.bsk.service.UserService;

public class DefaultController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showTable(Model model, Principal principal) {
		List<TableLabel> listOfReadableTables = userService.getReadableTables(principal);
		List<TableLabel> listOfWriteableTables = userService.getWriteableTables(principal);
		model.addAttribute("readeableTables", listOfReadableTables);
		model.addAttribute("writeableTables", listOfWriteableTables);
		return "showTables";
	}

}
