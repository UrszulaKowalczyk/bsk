package com.ukowalczyk.bsk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	@Column(nullable = true)
	private String description;

	@ManyToOne
	@JoinColumn(name = "label")
	private Label label;

	public Recipie(String title, String description, Label label) {
		this.title = title;
		this.description = description;
		this.label = label;
	}
}
