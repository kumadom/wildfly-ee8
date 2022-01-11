package com.example.app.com.jaxrs.filter;

import javax.validation.constraints.NotNull;

class CommonHeader {

	@NotNull(message = "{APYC00005}")
	private String traceId;

	String getTraceId() {
		return traceId;
	}

	void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	
}
