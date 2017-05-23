package com.ukowalczyk.bsk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

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
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	@Length(min = 1)
	@NotBlank
	private String login;

	@Column(nullable = false)
	@Length(min = 1)
	@NotBlank
	private String password;

	@Column(nullable = false)
	@Min(1)
	private int label;

	public User(String login, String password, int label) {
		this.login = login;
		this.password = password;
		this.label = label;
	}

}
