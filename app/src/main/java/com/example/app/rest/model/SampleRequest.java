package com.example.app.rest.model;

import org.apache.commons.lang3.StringUtils;

import com.example.app.com.json.bind.annotation.MaskedString;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class SampleRequest {

//	@NotNull(message = "{APYC00001}")
//	@Size(min = 6, max = 6, message = "{APYC00002}")
	// @JsonbTypeAdapter(CustomJsonAdaptor.class)
	// @JsonbTypeSerializer(CustomJsonbSerializer.class)
	@MaskedString
	private String businessData;

	@NotNull(message = "{APYC00001}")
	private String correlationData;

	@NotNull(message = "{APYC00001}")
	@Valid
	private SampleModel sampleModel;

	// @AssertTrue(message = "{APYC00004}")
	@JsonbTransient
	public boolean isCorrelationCheck() {
		return StringUtils.isNoneEmpty(businessData, correlationData);
	}

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

	public SampleModel getSampleModel() {
		return sampleModel;
	}

	public void setSampleModel(SampleModel sampleModel) {
		this.sampleModel = sampleModel;
	}

}
