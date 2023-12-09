package com.example.app.rest.resource;

public class ParentResponse<T> {
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
