package com.example.app.rest.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SampleRequest {

	@NotNull(message = "値の入力が必要")
	@Size(min = 6, max = 6, message = "半角で6文字の入力が必要")
	private String businessData;

	public String getBusinessData() {
		return businessData;
	}

	public void setBusinessData(String businessData) {
		this.businessData = businessData;
	}
	
	
}
