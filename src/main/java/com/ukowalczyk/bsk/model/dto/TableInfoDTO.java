package com.ukowalczyk.bsk.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TableInfoDTO {

	private String name;
	
	private List<String> columnsNames;

	private int level;

	private boolean canWrite;

	private boolean canRead;
}
