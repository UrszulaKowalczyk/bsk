package com.ukowalczyk.bsk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukowalczyk.bsk.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	public Ingredient findByName(String name);

}
