package com.ukowalczyk.bsk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.enums.LabelEnum;
import com.ukowalczyk.bsk.model.Label;
import com.ukowalczyk.bsk.repository.LabelRepository;

@Service
public class LabelService {

	@Autowired
	private LabelRepository repository;

	public void save(String value) {
		Label label = new Label(value);
		repository.save(label);
	}

	public Label findByValue(LabelEnum label) {
		return repository.findByValue(label.toString());
	}

}
