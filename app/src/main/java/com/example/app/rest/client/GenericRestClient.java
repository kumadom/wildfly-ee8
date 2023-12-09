package com.example.app.rest.client;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

public class GenericRestClient<T> {

	@Inject
	private Client client;
	
	public T execute() {
		Response res = client.target("https://reqres.in/api/users/2").request().get();
		T re = res.readEntity(setReturnTYpe());
		return re;
	}

	public GenericType<T> setReturnTYpe(){
		return new GenericType<T>() {};
	}
	
}
