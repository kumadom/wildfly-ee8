package com.example.app.com.core.exception;

import java.util.Map;

public class AppNotFoundException extends AppBusinessException {

	private static final long serialVersionUID = -1269354950271170980L;

	public AppNotFoundException(String errorCode, Map<String, String> args) {
		super(errorCode, args);
	}

}
