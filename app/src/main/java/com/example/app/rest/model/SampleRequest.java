package com.example.app.rest.model;

import javax.validation.constraints.NotNull;

public class SampleRequest {

	@NotNull
	private String businessData;

	public String getBusinessData() {
		return businessData;
	}

	public void setBusinessData(String businessData) {
		this.businessData = businessData;
	}
	
	
}
