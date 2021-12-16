package com.example.app.rest.model;

import javax.validation.constraints.NotNull;

public class Hoge {
	
	@NotNull(message = "{APYC00004}")
	private String gooooal;

	public String getGooooal() {
		return gooooal;
	}

	public void setGooooal(String gooooal) {
		this.gooooal = gooooal;
	}

}
