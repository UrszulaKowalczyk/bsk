package com.ukowalczyk.bsk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.model.RecipieIngredient;
import com.ukowalczyk.bsk.repository.RecipieIngredientRepository;

@Service
public class RecipieIngredientService {

	@Autowired
	private RecipieIngredientRepository repository;

	public void save(RecipieIngredient recipieIngredient) {
		repository.save(recipieIngredient);
	}
}
