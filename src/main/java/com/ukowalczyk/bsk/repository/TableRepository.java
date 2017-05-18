package com.ukowalczyk.bsk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukowalczyk.bsk.model.TableLabel;

public interface TableRepository extends JpaRepository<TableLabel, Long> {
	
	TableLabel findByTableName(String tableName);

	List<TableLabel> findAllByLabelLessThanEqual(int label);

	List<TableLabel> findAllByLabelGreaterThanEqual(int label);
	
}
