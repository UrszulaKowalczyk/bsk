package com.ukowalczyk.bsk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String roleName;

	public Role(String roleName) {
		this.roleName = roleName;
	}
}
