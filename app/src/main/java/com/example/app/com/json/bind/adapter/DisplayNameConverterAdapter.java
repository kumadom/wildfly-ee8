package com.example.app.com.json.bind.adapter;

import com.example.domain.service.SampleService;

import jakarta.inject.Inject;
import jakarta.json.bind.adapter.JsonbAdapter;

public class DisplayNameConverterAdapter implements JsonbAdapter<String, String> {

	@Inject
	private SampleService service;
	
	@Override
	public String adaptToJson(String obj) throws Exception {
		service.persist();
		return obj;
	}

	@Override
	public String adaptFromJson(String obj) throws Exception {
		return obj;
	}

}
