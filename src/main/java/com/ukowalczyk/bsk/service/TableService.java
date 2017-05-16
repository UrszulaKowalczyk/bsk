package com.ukowalczyk.bsk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.enums.LabelEnum;
import com.ukowalczyk.bsk.model.Label;
import com.ukowalczyk.bsk.model.TableLabels;
import com.ukowalczyk.bsk.repository.TableRepository;

@Service
public class TableService {

	@Autowired
	private TableRepository repository;
	@Autowired
	private LabelService labelService;

	public void save(TableLabels tableLabels) {
		repository.save(tableLabels);
	}
	
	public List<TableLabels> findAllByLowerLabel(LabelEnum userLabel){
		List<TableLabels> listOfTables = new ArrayList<>();
		for (LabelEnum label : LabelEnum.values()) {
			if (label.compareTo(userLabel) <= 0) {
				Label lowerLabel = labelService.findByValue(label);
				listOfTables.addAll(repository.findAllByLabel(lowerLabel));
			}
		}
		return listOfTables;
	}

	public List<TableLabels> findAllByHigherLabel(LabelEnum userLabel){
		List<TableLabels> listOfTables = new ArrayList<>();
		for (LabelEnum label : LabelEnum.values()) {
			if (label.compareTo(userLabel) >= 0) {
				Label lowerLabel = labelService.findByValue(label);
				listOfTables.addAll(repository.findAllByLabel(lowerLabel));
			}
		}
		return listOfTables;
	}
}
