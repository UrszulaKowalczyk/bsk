package com.ukowalczyk.bsk.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipieRequest {

	private String title;
	private String description;
	private String ingredients;
	private String label;

}
