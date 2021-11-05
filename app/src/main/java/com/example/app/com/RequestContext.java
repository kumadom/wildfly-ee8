package com.example.app.com;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestContext {

	private String str;

	public RequestContext() {
	}
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
}
