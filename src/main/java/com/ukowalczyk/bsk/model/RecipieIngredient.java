package com.ukowalczyk.bsk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
public class RecipieIngredient {

	@Embeddable
	@NoArgsConstructor
	@AllArgsConstructor
	@EqualsAndHashCode
	public static class Id implements Serializable {
		private static final long serialVersionUID = 595894804228146120L;
		@Column(name = "RECIPIE_ID")
		protected Long recipieId;
		@Column(name = "INGREDIENT_ID")
		protected Long ingredientId;
	}

	@EmbeddedId
	private Id id = new Id();

	@ManyToOne
	@JoinColumn(name = "RECIPIE_ID", insertable = false, updatable = false)
	private Recipie recipie;

	@ManyToOne
	@JoinColumn(name = "INGREDIENT_ID", insertable = false, updatable = false)
	private Ingredient ingredient;

	public RecipieIngredient(Recipie recipie, Ingredient ingredient) {
		this.id.ingredientId = ingredient.getId();
		this.id.recipieId = recipie.getId();
		ingredient.getRecipieIngredients().add(this);
		recipie.getRecipieIngredients().add(this);
	}

}
