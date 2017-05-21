package com.ukowalczyk.bsk.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ingredient")
	private List<Ingredient> ingredients;

	public Recipie(String title, String description, List<Ingredient> ingredients) {
		this.title = title;
		this.description = description;
		this.ingredients = ingredients;
	}

	public Recipie(String title, String description) {
		this.title = title;
		this.description = description;
		this.ingredients = Collections.emptyList();
	}

}
