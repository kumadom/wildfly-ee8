package com.example.app.com.jaxrs.exceptionMapper;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class CommonErrorResponse {
	private List<ErrorBody> errors;

	public CommonErrorResponse(List<ErrorBody> errors) {
		if (CollectionUtils.isEmpty(errors)) {
			throw new IllegalArgumentException();
		}
		this.errors = errors;
	}
	
	public List<ErrorBody> getErrors() {
		return errors;
	}

}
