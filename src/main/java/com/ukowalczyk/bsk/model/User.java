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
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String password;

	private int label;

	public User(String login, String password, int label) {
		this.login = login;
		this.password = password;
		this.label = label;
	}

}
