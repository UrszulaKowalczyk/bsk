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
public class TableLabels {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String tableName;

	@ManyToOne
	@JoinColumn(name = "label")
	private Label label;

	public TableLabels(String tableName, Label label) {
		this.tableName = tableName;
		this.label = label;
	}

}
