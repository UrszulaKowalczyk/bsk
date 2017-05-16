package com.ukowalczyk.bsk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.model.Label;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	public Ingredient findByName(String name);

}
