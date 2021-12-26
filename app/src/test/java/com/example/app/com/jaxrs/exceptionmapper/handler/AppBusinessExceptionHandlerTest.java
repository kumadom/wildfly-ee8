package com.example.app.com.jaxrs.exceptionmapper.handler;

import java.text.ParseException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class AppBusinessExceptionHandlerTest {
	
	@Test
	public void hoge() {
		
		AppBusinessExceptionHandler handler = new AppBusinessExceptionHandler();
		Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(null).build();
		
	}
	
	@Test
	public void fuga() throws ParseException {
	}
}
