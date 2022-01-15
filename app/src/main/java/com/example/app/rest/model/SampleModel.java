package com.example.app.rest.model;

import javax.validation.constraints.NotNull;

public class SampleModel {
	
	@NotNull(message = "{APYC00001}")
	private String gooooal;

	public String getGooooal() {
		return gooooal;
	}

	public void setGooooal(String gooooal) {
		this.gooooal = gooooal;
	}

}
