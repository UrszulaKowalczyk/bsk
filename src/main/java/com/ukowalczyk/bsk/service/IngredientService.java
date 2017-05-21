package com.ukowalczyk.bsk.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.initializer.DatabaseInitializer;
import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.model.User;
import com.ukowalczyk.bsk.repository.IngredientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IngredientService {

	@Autowired
	private IngredientRepository repository;

	@Autowired
	private UserService userService;

	public void save(Ingredient ingredient) {
		repository.save(ingredient);
	}

	public Ingredient findByName(String name) {
		return repository.findByName(name);
	}

	public List<Ingredient> findAll() {
		return repository.findAll();
	}

	public List<Ingredient> getAndAddIfNotExist(String[] ingredients, User user) {
		for (int i = 0; i < ingredients.length; i++) {
			if (repository.findByName(ingredients[i]) == null) {
				log.info(ingredients[i]);
				repository.save(new Ingredient(ingredients[i]));
			}
		}
		List<Ingredient> listOfIngredients = new ArrayList<>();
		for (int i = 0; i < ingredients.length; i++) {
			listOfIngredients.add(repository.findByName(ingredients[i]));
		}
		return listOfIngredients;
	}

	public List<Ingredient> create(String[] ingredients, Principal principal) {
		if (!userService.checkIfUserCanWrite(principal, DatabaseInitializer.TABLE_INGREDIENT))
			return Collections.emptyList();

		List<Ingredient> listOfIngredients = new ArrayList<>();
		for (String ingredientName : ingredients) {
			Ingredient ingredient = new Ingredient(ingredientName);
			listOfIngredients.add(ingredient);
		}
		return repository.save(listOfIngredients);
	}

	public Ingredient find(Long id) {
		return repository.findOne(id);
	}

}
