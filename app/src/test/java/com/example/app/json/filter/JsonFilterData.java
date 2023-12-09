package com.example.app.json.filter;

import java.util.List;

public class JsonFilterData {

	private JsonFilterData data;
	private String value;
	private Integer i;
	private List<JsonFilterData> li;
	
	public JsonFilterData() {
	}

	public JsonFilterData(JsonFilterData data, String value) {
		this.data = data;
		this.value = value;
	}
	
	public JsonFilterData getData() {
		return data;
	}

	public JsonFilterData setData(JsonFilterData data) {
		this.data = data;
		return this;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getI() {
		return i;
	}

	public JsonFilterData setI(Integer i) {
		this.i = i;
		return this;
	}

	public List<JsonFilterData> getLi() {
		return li;
	}

	public JsonFilterData setLi(List<JsonFilterData> li) {
		this.li = li;
		return this;
	}

}
