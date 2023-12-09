package com.example.app.json.filter;

public class JsonFilterData {

	private JsonFilterData data;
	private String value;
	
	public JsonFilterData() {
	}

	public JsonFilterData(JsonFilterData data, String value) {
		this.data = data;
		this.value = value;
	}
	
	public JsonFilterData getData() {
		return data;
	}

	public void setData(JsonFilterData data) {
		this.data = data;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
