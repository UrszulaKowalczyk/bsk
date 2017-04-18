package com.ukowalczyk.bsk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukowalczyk.bsk.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findById(Long id);

	Role findByRoleName(String roleName);
}
