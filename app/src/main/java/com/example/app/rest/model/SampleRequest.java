package com.example.app.rest.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SampleRequest {

	@NotNull(message = "値の入力が必要")
	@Size(min = 3, max = 3, message = "{min}以上、{max}以下の値で設定してください")
	private String businessData;

	public String getBusinessData() {
		return businessData;
	}

	public void setBusinessData(String businessData) {
		this.businessData = businessData;
	}
	
	
}
