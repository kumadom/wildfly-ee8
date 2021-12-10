package com.example.app.rest.model;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

public class SampleRequest {

	@NotNull(message = "{APYC00001}")
	@Size(min = 6, max = 6, message = "{APYC00002}")
	private String businessData;
	
	@NotNull(message = "{APYC00001}")
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

	@AssertTrue(message = "{APYC00004}")
	public boolean isCorrelationCheck() {
		return StringUtils.isNoneEmpty(businessData, correlationData);
	}
	
}
