package com.ukowalczyk.bsk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukowalczyk.bsk.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findById(Long id);

}
