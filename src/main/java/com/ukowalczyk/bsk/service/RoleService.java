package com.ukowalczyk.bsk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.model.Role;
import com.ukowalczyk.bsk.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	public void create(Role role) {
		repository.save(role);
	}

	public Role findByRoleName(String roleName) {
		return repository.findByRoleName(roleName);
	}
}
