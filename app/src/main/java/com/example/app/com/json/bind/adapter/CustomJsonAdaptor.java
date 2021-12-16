package com.example.app.com.json.bind.adapter;

import javax.json.bind.adapter.JsonbAdapter;

public class CustomJsonAdaptor implements JsonbAdapter<String, String> {

	@Override
	public String adaptToJson(String obj) throws Exception {
		return "**************";
	}

	@Override
	public String adaptFromJson(String obj) throws Exception {
		System.out.println("hoghohooooooooooooooooooooooo");
		System.out.println("hoghohooooooooooooooooooooooo");
		System.out.println("hoghohooooooooooooooooooooooo");
		System.out.println("hoghohooooooooooooooooooooooo");
		System.out.println(obj);
		
		System.out.println("hoghohooooooooooooooooooooooo");
		System.out.println("hoghohooooooooooooooooooooooo");
		System.out.println("hoghohooooooooooooooooooooooo");
		return obj;
	}

}
