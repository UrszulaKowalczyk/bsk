package com.ukowalczyk.bsk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukowalczyk.bsk.model.Recipie;

public interface RecipieRepository extends JpaRepository<Recipie, Long> {

	public Recipie findById(Long id);

}
