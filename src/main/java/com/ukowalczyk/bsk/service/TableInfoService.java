package com.ukowalczyk.bsk.service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukowalczyk.bsk.initializer.DatabaseInitializer;
import com.ukowalczyk.bsk.model.dto.TableInfoDTO;
import com.ukowalczyk.bsk.model.dto.TableInfoDTO.TableInfoDTOBuilder;

import lombok.NonNull;

@Service
public class TableInfoService {

	@Autowired
	private UserService userService;

	@Autowired
	private TableLabelService tableLabelService;

	public TableInfoDTO getTableInfo(@NonNull String tableName, @NonNull Principal principal) {

		List<String> ingredientColumns = Arrays.asList("id", "name");
		List<String> tableLabelColumns = Arrays.asList("id", "tableName", "label");
		List<String> recipieColumns = Arrays.asList("id", "title", "description");
		List<String> recipieIngredientsColumns = Arrays.asList("id", "recipie_id", "ingredient_id");
		List<String> userColumns = Arrays.asList("id", "login", "password", "label");
		
		TableInfoDTOBuilder builder = TableInfoDTO.builder()
			.canWrite(userService.checkIfUserCanWrite(principal, tableName))
			.canRead(userService.checkIfUserCanRead(principal, tableName))
			.level(tableLabelService.findByTableName(tableName).getLabel());
		
		switch (tableName) {
		
			case DatabaseInitializer.TABLE_TABLELABEL:
				return builder
					.name(DatabaseInitializer.TABLE_TABLELABEL)
					.columnsNames(tableLabelColumns)
					.build();
				
			case DatabaseInitializer.TABLE_USER:
				return builder
					.name(DatabaseInitializer.TABLE_USER)
					.columnsNames(userColumns)
					.build();
		
			case DatabaseInitializer.TABLE_INGREDIENT:
				return builder
					.name(DatabaseInitializer.TABLE_INGREDIENT)
					.columnsNames(ingredientColumns)
					.build();
				
			case DatabaseInitializer.TABLE_RECIPIE:
				return builder
					.name(DatabaseInitializer.TABLE_RECIPIE)
					.columnsNames(recipieColumns)
					.build();
				
			case DatabaseInitializer.TABLE_RECIPIE_INGREDIENT:
				return builder
					.name(DatabaseInitializer.TABLE_RECIPIE_INGREDIENT)
					.columnsNames(recipieIngredientsColumns)
					.build();
				
		default:
			throw new RuntimeException("Table " + tableName + " not exists.");
		}
	}
}
