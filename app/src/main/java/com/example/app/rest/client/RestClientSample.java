package com.example.app.rest.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("rest")
@ApplicationScoped
public class RestClientSample {

	@GET
	public void hoge() {
	}
	
}
