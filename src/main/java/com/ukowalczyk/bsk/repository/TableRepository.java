package com.ukowalczyk.bsk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukowalczyk.bsk.model.Label;
import com.ukowalczyk.bsk.model.TableLabels;

public interface TableRepository extends JpaRepository<TableLabels, Long> {
	public List<TableLabels> findAllByLabel(Label label);

}

