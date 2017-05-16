package com.ukowalczyk.bsk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukowalczyk.bsk.model.Label;
import com.ukowalczyk.bsk.model.Recipie;

public interface RecipieRepository extends JpaRepository<Recipie, Long> {

	public Recipie findById(Long id);

}
