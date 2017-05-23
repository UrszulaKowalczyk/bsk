package com.ukowalczyk.bsk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableLabel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private int label;

	@Column(nullable = false, unique = true)
	private String tableName;

	public TableLabel(String tableName, int label) {
		this.tableName = tableName;
		this.label = label;
	}

	public TableLabel(int label, String tableName) {
		this.tableName = tableName;
		this.label = label;
	}

}
