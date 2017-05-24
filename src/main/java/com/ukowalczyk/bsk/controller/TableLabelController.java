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
import com.ukowalczyk.bsk.model.TableLabel;
import com.ukowalczyk.bsk.service.TableLabelService;
import com.ukowalczyk.bsk.service.UserService;

@Controller
public class TableLabelController extends AbstractController {

	@Autowired
	private UserService userService;

	@Autowired
	private TableLabelService tableLabelService;

	@Autowired
	private DefaultController defaultController;

	@RequestMapping(value = "/tableLabel")
	public String user(Model model, Principal principal) {
		model.addAttribute("shownTable", "tableLabel");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/addTableLabel", method = RequestMethod.POST)
	public String createTableLabel(HttpServletRequest req, HttpServletResponse res, Model model, Principal principal) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_TABLELABEL))
			return "notAllowed";

		String[] label = req.getParameterValues("label");
		String[] tableName = req.getParameterValues("tableName");

		TableLabel tableLabel = new TableLabel(Integer.parseInt(label[0]), tableName[0]);
		tableLabelService.save(tableLabel);

		model.addAttribute("shownTable", "tableLabel");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/updateTableLabel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String updateTableLabel(@RequestBody TableLabel updatedTableLabel, Principal principal, Model model) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_RECIPIE)) {
			model.addAttribute("shownTable", "tableLabel");
			return defaultController.showTable(model, principal);
		}

		tableLabelService.save(updatedTableLabel);

		model.addAttribute("shownTable", "tableLabel");
		return defaultController.showTable(model, principal);
	}

	@RequestMapping(value = "/deleteTableLabel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String delete(@RequestBody TableLabel tableLabel, Principal principal, Model model) {

		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_TABLELABEL)) {
			model.addAttribute("shownTable", "tableLabel");
			return defaultController.showTable(model, principal);
		}

		tableLabelService.deleteById(tableLabel.getId());

		model.addAttribute("shownTable", "tableLabel");
		return defaultController.showTable(model, principal);
	}

}
