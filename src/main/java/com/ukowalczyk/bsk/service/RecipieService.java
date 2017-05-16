package com.ukowalczyk.bsk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.model.Label;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.repository.RecipieRepository;

@Service
public class RecipieService {

	@Autowired
	private RecipieRepository repository;

	public List<Recipie> findAll() {
		return repository.findAll();
	}

	public void save(Recipie recipie) {
		repository.save(recipie);
	}

}
