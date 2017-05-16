package com.ukowalczyk.bsk.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.enums.LabelEnum;
import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.model.Label;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.model.TableLabels;
import com.ukowalczyk.bsk.model.User;
import com.ukowalczyk.bsk.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private RecipieService recipieService;
	@Autowired
	private LabelService labelService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private TableService tableService;

	public List<Recipie> getRecipies(Principal principal) {
		if (!checkIfUserCanRead(principal, "recipie"))
			return null;
		return recipieService.findAll();
	}

	public boolean checkIfUserCanRead(Principal principal, String tableName) {
		List<TableLabels> readableTables = getReadableTables(principal);
		for (TableLabels tableLabels : readableTables) {
			if (tableLabels.getTableName().equals(tableName))
				return true;
		}
		return false;
	}

	public boolean checkIfUserCanWrite(Principal principal, String tableName) {
		List<TableLabels> writeableTables = getWriteableTables(principal);
		for (TableLabels tableLabels : writeableTables) {
			if (tableLabels.getTableName().equals(tableName))
				return true;
		}
		return false;
	}

	public List<Ingredient> getIngredients(Principal principal) {
		if (!checkIfUserCanRead(principal, "ingredient"))
			return null;
		return ingredientService.findAll();
	}

	public List<TableLabels> getReadableTables(Principal principal) {
		User user = findByLogin(principal);
		LabelEnum userLabel = getUserLabel(user);
		return tableService.findAllByLowerLabel(userLabel);
	}

	public List<TableLabels> getWriteableTables(Principal principal) {
		User user = findByLogin(principal);
		LabelEnum userLabel = getUserLabel(user);
		return tableService.findAllByHigherLabel(userLabel);
	}

	private List<Label> getHigherLabels(Principal principal) {
		User user = findByLogin(principal);
		LabelEnum userLabel = getUserLabel(user);
		List<Label> listOfLabels = new ArrayList<>();
		for (LabelEnum label : LabelEnum.values()) {
			if (label.compareTo(userLabel) >= 0) {
				Label higherLabel = labelService.findByValue(label);
				listOfLabels.add(higherLabel);
			}
		}
		return listOfLabels;
	}

	public void save(User user) {
		repository.save(user);
	}

	public User findByLogin(Principal principal) {
		return repository.findByLogin(principal.getName());
	}

	private LabelEnum getUserLabel(User user) {
		String userLabel = user.getLabel().getValue();
		for (LabelEnum label : LabelEnum.values()) {
			if (label.toString().equals(userLabel))
				return label;
		}
		return null;
	}

}
