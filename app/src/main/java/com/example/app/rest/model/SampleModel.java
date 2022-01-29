package com.example.app.rest.model;

import javax.validation.constraints.NotNull;

import com.example.app.com.json.bind.annotation.MaskInfomation;

public class SampleModel {
	
	@NotNull(message = "{APYC00001}")
	@MaskInfomation
	private String gooooal;

	public String getGooooal() {
		return gooooal;
	}

	public void setGooooal(String gooooal) {
		this.gooooal = gooooal;
	}

}
