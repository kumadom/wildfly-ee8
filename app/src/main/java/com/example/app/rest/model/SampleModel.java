package com.example.app.rest.model;

import javax.validation.constraints.NotNull;

import com.example.app.com.json.bind.annotation.MaskedString;

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
