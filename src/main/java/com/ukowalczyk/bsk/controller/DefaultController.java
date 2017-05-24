package com.ukowalczyk.bsk.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ukowalczyk.bsk.initializer.DatabaseInitializer;
import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.model.RecipieIngredient;
import com.ukowalczyk.bsk.model.TableLabel;
import com.ukowalczyk.bsk.model.User;
import com.ukowalczyk.bsk.model.dto.TableInfoDTO;
import com.ukowalczyk.bsk.service.IngredientService;
import com.ukowalczyk.bsk.service.RecipieIngredientService;
import com.ukowalczyk.bsk.service.RecipieService;
import com.ukowalczyk.bsk.service.TableInfoService;
import com.ukowalczyk.bsk.service.TableLabelService;
import com.ukowalczyk.bsk.service.UserService;

@Controller
public class DefaultController extends AbstractController {

	@Autowired
	private TableInfoService tableInfoService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private RecipieService recipieService;

	@Autowired
	private UserService userService;

	@Autowired
	private TableLabelService tableLabelService;

	@Autowired
	private RecipieIngredientService recipieIngredientService;

	@Autowired
	private UserController userController;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectToUserTable(Model model, Principal principal) {
		return userController.user(model, principal);
	}

	public String showTable(Model model, Principal principal) {

		model.addAttribute("user", principal.getName());

		TableInfoDTO ingredientTableInfo = tableInfoService.getTableInfo(DatabaseInitializer.TABLE_INGREDIENT,
				principal);
		TableInfoDTO recipieTableInfo = tableInfoService.getTableInfo(DatabaseInitializer.TABLE_RECIPIE, principal);
		TableInfoDTO recipieIngredientTableInfo = tableInfoService
				.getTableInfo(DatabaseInitializer.TABLE_RECIPIE_INGREDIENT, principal);
		TableInfoDTO userTableInfo = tableInfoService.getTableInfo(DatabaseInitializer.TABLE_USER, principal);
		TableInfoDTO tableLabelTableInfo = tableInfoService.getTableInfo(DatabaseInitializer.TABLE_TABLELABEL,
				principal);

		model.addAttribute("ingredientTableInfo", ingredientTableInfo);
		model.addAttribute("recipieTableInfo", recipieTableInfo);
		model.addAttribute("recipieIngredientTableInfo", recipieIngredientTableInfo);
		model.addAttribute("userTableInfo", userTableInfo);
		model.addAttribute("tableLabelTableInfo", tableLabelTableInfo);

		List<Ingredient> ingredientList = (ingredientTableInfo.isCanRead()) ? ingredientService.findAll()
				: Collections.emptyList();
		List<Recipie> recipieList = (recipieTableInfo.isCanRead()) ? recipieService.findAll() : Collections.emptyList();
		List<RecipieIngredient> recipieIngredientList = (recipieIngredientTableInfo.isCanRead())
				? recipieIngredientService.findAll() : Collections.emptyList();
		List<User> userList = (userTableInfo.isCanRead()) ? userService.findAll() : Collections.emptyList();
		List<TableLabel> tableLabelList = (tableLabelTableInfo.isCanRead()) ? tableLabelService.findAll()
				: Collections.emptyList();

		model.addAttribute("ingredientList", ingredientList);
		model.addAttribute("recipieList", recipieList);
		model.addAttribute("recipieIngredientList", recipieIngredientList);
		model.addAttribute("userList", userList);
		model.addAttribute("tableLabelList", tableLabelList);

		return "showTables";
	}

}
