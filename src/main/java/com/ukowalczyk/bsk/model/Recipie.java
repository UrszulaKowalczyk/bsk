package com.ukowalczyk.bsk.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipie {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = true, length = 2000)
	private String description;

	@OneToMany(mappedBy = "recipie", fetch = FetchType.EAGER)
	private List<RecipieIngredient> recipieIngredients = new ArrayList<>();

	public Recipie(String title, String description, List<RecipieIngredient> recipieIngredients) {
		this.title = title;
		this.description = description;
		this.recipieIngredients = recipieIngredients;
	}

	public Recipie(String title, String description) {
		this.title = title;
		this.description = description;
		this.recipieIngredients =  new ArrayList<>();
	}

}
