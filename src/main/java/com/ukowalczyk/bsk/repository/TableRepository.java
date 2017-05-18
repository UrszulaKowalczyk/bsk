package com.ukowalczyk.bsk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukowalczyk.bsk.model.TableLabel;

public interface TableRepository extends JpaRepository<TableLabel, Long> {
	public List<TableLabel> findAllByLabelLessThanEqual(int label);
	public List<TableLabel> findAllByLabelGreaterThanEqual(int label);
}
