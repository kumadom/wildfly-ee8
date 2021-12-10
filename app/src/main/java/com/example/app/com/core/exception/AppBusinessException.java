package com.example.app.com.core.exception;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AppBusinessException extends RuntimeException {

	private static final long serialVersionUID = -7695764057353201465L;
	
	private List<ErrorDetail> errorDetails;
	
	public AppBusinessException(String errorCode, Map<String, String> args) {
		ErrorDetail detail = new ErrorDetail(errorCode, args);
		this.errorDetails = Arrays.asList(detail);
	}
	
	public AppBusinessException addError(String errorCode, Map<String, String> args) {
		ErrorDetail detail = new ErrorDetail(errorCode, args);
		errorDetails.add(detail);
		return this;
	}
	
	public List<ErrorDetail> getErrorDetails(){
		return Collections.unmodifiableList(errorDetails);
	}
	
}
