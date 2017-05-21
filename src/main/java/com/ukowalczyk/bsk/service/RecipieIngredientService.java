package com.ukowalczyk.bsk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.model.RecipieIngredient;
import com.ukowalczyk.bsk.repository.RecipieIngredientRepository;

@Service
public class RecipieIngredientService {

	@Autowired
	private RecipieIngredientRepository repository;

	@Autowired
	private RecipieService recipieService;

	@Autowired
	private IngredientService ingredientService;

	public void save(RecipieIngredient recipieIngredient) {
		repository.save(recipieIngredient);
	}

	public List<RecipieIngredient> findAll() {
		return repository.findAll();
	}

	public RecipieIngredient create(Long recipieId, Long ingredientId) {
		Recipie recipie = recipieService.findOne(recipieId);
		Ingredient ingredient = ingredientService.findOne(ingredientId);
		RecipieIngredient recipieIngredient = new RecipieIngredient(recipie, ingredient);
		return repository.save(recipieIngredient);
	}

	public RecipieIngredient create(String recipieId, String ingredientId) {
		return create(Long.parseLong(recipieId), Long.parseLong(ingredientId));
	}

}
