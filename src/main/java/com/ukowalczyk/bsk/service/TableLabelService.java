package com.ukowalczyk.bsk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.model.TableLabel;
import com.ukowalczyk.bsk.repository.TableRepository;

@Service
public class TableLabelService {

	@Autowired
	private TableRepository repository;

	public void save(TableLabel tableLabel) {
		repository.save(tableLabel);
	}

	public TableLabel findByTableName(String name) {
		return repository.findByTableName(name);
	}

	public List<TableLabel> findAllByLabelLessThanEqual(int userLabel) {
		return repository.findAllByLabelLessThanEqual(userLabel);
	}

	public List<TableLabel> findAllByLabelGreaterThanEqual(int userLabel) {
		return repository.findAllByLabelGreaterThanEqual(userLabel);
	}

	public List<TableLabel> findAll() {
		return repository.findAll();
	}

}
