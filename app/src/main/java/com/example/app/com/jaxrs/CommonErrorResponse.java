package com.example.app.com.jaxrs;

import java.util.List;

public class CommonErrorResponse {
	private List<ErrorDetail> errors;

	public List<ErrorDetail> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDetail> errors) {
		this.errors = errors;
	}
	
}
