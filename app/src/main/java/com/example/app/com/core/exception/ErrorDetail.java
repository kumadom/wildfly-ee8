package com.example.app.com.core.exception;

import java.util.Map;

public class ErrorDetail {

	private String code;
	private Map<String, String> args;
	
	ErrorDetail(String code, Map<String, String> args) {
		this.code = code;
		this.args = args;
	}
	
	public String getCode() {
		return code;
	}
	public Map<String, String> getArgs() {
		return args;
	}

}
