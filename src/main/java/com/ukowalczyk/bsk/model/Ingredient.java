package com.ukowalczyk.bsk.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@Length(min = 1)
	@NotBlank
	private String name;

	@OneToMany(mappedBy = "ingredient", fetch = FetchType.EAGER)
	private List<RecipieIngredient> recipieIngredients = new ArrayList<>();

	public Ingredient(String name) {
		this.name = name;
	}

}
