package com.example.app.com.jaxrs.request.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ErrorDetailInfo {

	private String errorCode;
	private String errorMessage;
	private List<String> errorInfo;
	public ErrorDetailInfo(String errorCode, String errorMessage, List<String> errorInfo) {
		if (StringUtils.isAnyEmpty(errorCode, errorMessage)) {
			throw new IllegalArgumentException();
		}
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorInfo = errorInfo;
	}
	public ErrorDetailInfo(String errorCode, String errorMessage) {
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
	public List<String> getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(List<String> errorInfo) {
		this.errorInfo = errorInfo;
	}
	
}
