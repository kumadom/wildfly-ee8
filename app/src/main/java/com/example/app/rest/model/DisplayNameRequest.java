package com.example.app.rest.model;

import com.example.app.com.json.bind.annotation.DisplayName;

public class DisplayNameRequest {

	@DisplayName
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
