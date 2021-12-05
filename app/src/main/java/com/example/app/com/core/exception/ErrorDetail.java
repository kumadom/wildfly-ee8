package com.example.app.com.core.exception;

public class ErrorDetail {

	private String code;
	private String[] args;
	
	ErrorDetail(String code, String[] args) {
		this.code = code;
		this.args = args;
	}
	
	public String getCode() {
		return code;
	}
	public String[] getArgs() {
		return args;
	}

}
