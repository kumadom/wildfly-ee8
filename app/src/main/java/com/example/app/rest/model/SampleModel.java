package com.example.app.rest.model;

import com.example.app.com.json.bind.annotation.MaskedString;

import jakarta.validation.constraints.NotNull;

public class SampleModel {

	@NotNull(message = "{APYC00001}")
	@MaskedString
	private String gooooal;

	public String getGooooal() {
		return gooooal;
	}

	public void setGooooal(String gooooal) {
		this.gooooal = gooooal;
	}

}
