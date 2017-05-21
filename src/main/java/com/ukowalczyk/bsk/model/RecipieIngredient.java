package com.ukowalczyk.bsk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecipieIngredient {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "RECIPIE_ID")
	private Long recipieId;
	@Column(name = "INGREDIENT_ID")
	private Long ingredientId;

	@ManyToOne
	@JoinColumn(name = "RECIPIE_ID", insertable = false, updatable = false)
	private Recipie recipie;

	@ManyToOne
	@JoinColumn(name = "INGREDIENT_ID", insertable = false, updatable = false)
	private Ingredient ingredient;

	public RecipieIngredient(Recipie recipie, Ingredient ingredient) {
		this.ingredientId = ingredient.getId();
		this.recipieId = recipie.getId();
		ingredient.getRecipieIngredients().add(this);
		recipie.getRecipieIngredients().add(this);
	}

}
