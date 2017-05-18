package com.ukowalczyk.bsk.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.model.TableLabel;
import com.ukowalczyk.bsk.model.User;
import com.ukowalczyk.bsk.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private RecipieService recipieService;
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
		List<TableLabel> readableTables = getReadableTables(principal);
		for (TableLabel tableLabel : readableTables) {
			if (tableLabel.getTableName().equals(tableName))
				return true;
		}
		return false;
	}

	public boolean checkIfUserCanWrite(Principal principal, String tableName) {
		List<TableLabel> writeableTables = getWriteableTables(principal);
		for (TableLabel tableLabel : writeableTables) {
			if (tableLabel.getTableName().equals(tableName))
				return true;
		}
		return false;
	}

	public List<Ingredient> getIngredients(Principal principal) {
		if (!checkIfUserCanRead(principal, "ingredient"))
			return null;
		return ingredientService.findAll();
	}

	public List<TableLabel> getReadableTables(Principal principal) {
		User user = findByLogin(principal);
		return tableService.findAllByLabelLessThanEqual(user.getLabel());
	}

	public List<TableLabel> getWriteableTables(Principal principal) {
		User user = findByLogin(principal);
		return tableService.findAllByLabelGreaterThanEqual(user.getLabel());
	}

	public void save(User user) {
		repository.save(user);
	}

	public User findByLogin(Principal principal) {
		return repository.findByLogin(principal.getName());
	}

}
