package com.example.app.rest.model;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

public class SampleRequest {

	@NotNull(message = "値の入力が必要")
	@Size(min = 6, max = 6, message = "半角で6文字の入力が必要")
	private String businessData;
	
	@NotNull(message = "値の入力が必要")
	private String correlationData;
	
	public String getBusinessData() {
		return businessData;
	}

	public void setBusinessData(String businessData) {
		this.businessData = businessData;
	}	
	
	public String getCorrelationData() {
		return correlationData;
	}

	public void setCorrelationData(String correlationData) {
		this.correlationData = correlationData;
	}

	@AssertTrue(message = "値の入力が両方とも必要")
	public boolean isCorrelationCheck() {
		return StringUtils.isNoneEmpty(businessData, correlationData);
	}
	
}
