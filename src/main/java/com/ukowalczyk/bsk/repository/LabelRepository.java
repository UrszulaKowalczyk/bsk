package com.ukowalczyk.bsk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukowalczyk.bsk.model.Label;

public interface LabelRepository extends JpaRepository<Label, Long> {

	public Label findByValue(String value);
}
