package com.ukowalczyk.bsk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukowalczyk.bsk.model.RecipieIngredient;

public interface RecipieIngredientRepository extends JpaRepository<RecipieIngredient, Long> {

}
