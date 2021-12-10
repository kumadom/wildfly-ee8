package com.example.app.com.core.exception;

import java.util.Map;
import java.util.Optional;

public class ErrorDetail {

	private String code;
	private Optional<Map<String, String>> args;
	
	ErrorDetail(String code, Map<String, String> args) {
		this.code = code;
		this.args = Optional.ofNullable(args);
	}
	
	public String getCode() {
		return code;
	}
	public Optional<Map<String, String>> getArgs() {
		return args;
	}

}
