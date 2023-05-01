package com.example.app.com.jaxrs.exceptionmapper.handler;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class AppBusinessExceptionHandlerTest {
	
	public void hoge() {
		
		AppBusinessExceptionHandler handler = new AppBusinessExceptionHandler();
		Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(null).build();
		
	}
	
}
