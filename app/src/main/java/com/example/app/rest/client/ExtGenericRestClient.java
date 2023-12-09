package com.example.app.rest.client;

import com.example.app.rest.resource.ChildResponse;
import com.example.app.rest.resource.ParentResponse;

import jakarta.ws.rs.core.GenericType;

public class ExtGenericRestClient extends GenericRestClient<ParentResponse<ChildResponse>> {

	@Override
	public GenericType<ParentResponse<ChildResponse>> setReturnTYpe() {
		// TODO Auto-generated method stub
		return new GenericType<ParentResponse<ChildResponse>>() {};
	}
	
}
