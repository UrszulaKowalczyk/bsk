package com.ukowalczyk.bsk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.model.User;
import com.ukowalczyk.bsk.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public void save(User user) {
		repository.save(user);
	}

}
