package com.ukowalczyk.bsk.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.enums.LabelEnum;
import com.ukowalczyk.bsk.model.Label;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.model.User;
import com.ukowalczyk.bsk.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private RecipieService recipieService;
	@Autowired
	private LabelService labelService;

	public List<Recipie> getVisibleRecipies(Principal principal) {
		User user = findByLogin(principal);
		LabelEnum userLabel = getUserLabel(user);
		List<Recipie> listOfRecipies = new ArrayList<>();
		for (LabelEnum label : LabelEnum.values()) {
			if (label.compareTo(userLabel) <= 0) {
				Label lowerLabel = labelService.findByValue(label);
				listOfRecipies.addAll(recipieService.findAllByLabel(lowerLabel));
			}
		}
		return listOfRecipies;

	}

	public List<Label> getHigherLabels(Principal principal) {
		User user = findByLogin(principal);
		LabelEnum userLabel = getUserLabel(user);
		List<Label> listOfLabels = new ArrayList<>();
		for (LabelEnum label : LabelEnum.values()) {
			if (label.compareTo(userLabel) >= 0) {
				Label higherLabel = labelService.findByValue(label);
				listOfLabels.add(higherLabel);
			}
		}
		return listOfLabels;
	}

	public void save(User user) {
		repository.save(user);
	}

	private User findByLogin(Principal principal) {
		return repository.findByLogin(principal.getName());
	}

	private LabelEnum getUserLabel(User user) {
		String userLabel = user.getLabel().getValue();
		for (LabelEnum label : LabelEnum.values()) {
			if (label.toString().equals(userLabel))
				return label;
		}
		return null;
	}

}
