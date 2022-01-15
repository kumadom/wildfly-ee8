package com.example.app.com.core.model;

import javax.validation.constraints.NotNull;

public class CommonHeader {

	@NotNull(message = "{APYC00005}")
	private String traceId;

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	
}
