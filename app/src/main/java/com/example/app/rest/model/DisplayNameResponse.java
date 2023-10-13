package com.example.app.rest.model;

import com.example.app.com.json.bind.annotation.DisplayName;

import jakarta.validation.constraints.Size;

public class DisplayNameResponse {

	@Size(min = 1, max = 100)
	@DisplayName
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
