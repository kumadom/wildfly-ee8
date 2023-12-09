package com.example.app.rest;

import java.util.List;

import jakarta.validation.constraints.Size;

public class KaramojiSample {

	@Size(min = 1)
	private List<String>list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
}
