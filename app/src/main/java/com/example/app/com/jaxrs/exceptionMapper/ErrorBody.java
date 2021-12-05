package com.example.app.com.jaxrs.exceptionMapper;

import org.apache.commons.lang3.StringUtils;

public class ErrorBody {

	private String errorCode;
	private String errorMessage;
	public ErrorBody(String errorCode, String errorMessage) {
		if (StringUtils.isAnyEmpty(errorCode, errorMessage)) {
			throw new IllegalArgumentException();
		}
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
//	public void setErrorCode(String errorCode) {
//		this.errorCode = errorCode;
//	}
	public String getErrorMessage() {
		return errorMessage;
	}
//	public void setErrorMessage(String errorMessage) {
//		this.errorMessage = errorMessage;
//	}
	
}
