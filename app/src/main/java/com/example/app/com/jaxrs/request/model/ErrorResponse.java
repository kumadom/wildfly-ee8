package com.example.app.com.jaxrs.request.model;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class ErrorResponse {
	private List<ErrorDetailInfo> errorDetailInfo;

	public ErrorResponse(List<ErrorDetailInfo> errors) {
		if (CollectionUtils.isEmpty(errors)) {
			throw new IllegalArgumentException();
		}
		this.errorDetailInfo = errors;
	}

	public List<ErrorDetailInfo> getErrorDetailInfo() {
		return errorDetailInfo;
	}

	public void setErrorDetailInfo(List<ErrorDetailInfo> errorDetailInfo) {
		this.errorDetailInfo = errorDetailInfo;
	}
	
}
