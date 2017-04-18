package com.ukowalczyk.bsk.initializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ukowalczyk.bsk.enums.LabelEnum;
import com.ukowalczyk.bsk.model.Label;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.model.Role;
import com.ukowalczyk.bsk.model.User;
import com.ukowalczyk.bsk.service.LabelService;
import com.ukowalczyk.bsk.service.RecipieService;
import com.ukowalczyk.bsk.service.RoleService;
import com.ukowalczyk.bsk.service.UserService;

@Component
public class DatabaseInitializer {

	@Autowired
	private LabelService labelService;
	@Autowired
	private UserService userService;
	@Autowired
	private RecipieService recipieService;
	@Autowired
	private RoleService roleService;

	private static final String USER_ROLE_NAME = "ROLE_USER";
	private static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";

	@PostConstruct
	public void initializeDatabase() {
		initializeRoles();
		initializeLabels();
		initializeUsers();
		initializeRecipies();
	}

	private void initializeRecipies() {
		Label label1 = labelService.findByValue(LabelEnum.UNCLASSIFIED);
		Recipie recipie1 = new Recipie("title1", "description1", label1);
		recipieService.save(recipie1);
		Label label2 = labelService.findByValue(LabelEnum.CONFIDENTIAL);
		Recipie recipie2 = new Recipie("title2", "description2", label2);
		recipieService.save(recipie2);
		Label label3 = labelService.findByValue(LabelEnum.SECRET);
		Recipie recipie3 = new Recipie("title3", "description3", label3);
		recipieService.save(recipie3);
		Label label4 = labelService.findByValue(LabelEnum.TOP_SECRET);
		Recipie recipie4 = new Recipie("title4", "description4", label4);
		recipieService.save(recipie4);
	}

	private void initializeUsers() {
		Role userRole = roleService.findByRoleName(USER_ROLE_NAME);
		
		Label label1 = labelService.findByValue(LabelEnum.UNCLASSIFIED);
		User user1 = new User("username1", "password1", label1, userRole);
		userService.save(user1);
		Label label2 = labelService.findByValue(LabelEnum.CONFIDENTIAL);
		User user2 = new User("username2", "password2", label2, userRole);
		userService.save(user2);
		Label label3 = labelService.findByValue(LabelEnum.SECRET);
		User user3 = new User("username3", "password3", label3, userRole);
		userService.save(user3);
		Label label4 = labelService.findByValue(LabelEnum.TOP_SECRET);
		User user4 = new User("username4", "password4", label4, userRole);
		userService.save(user4);
	}

	private void initializeLabels() {
		for (LabelEnum label : LabelEnum.values()) {
			labelService.save(label.toString());
		}
	}

	private void initializeRoles() {
		if (roleService.findByRoleName(USER_ROLE_NAME) == null) {
			Role userRole = new Role(USER_ROLE_NAME);
			roleService.create(userRole);
		}
		if (roleService.findByRoleName(ADMIN_ROLE_NAME) == null) {
			Role adminRole = new Role(ADMIN_ROLE_NAME);
			roleService.create(adminRole);
		}
	}

}
