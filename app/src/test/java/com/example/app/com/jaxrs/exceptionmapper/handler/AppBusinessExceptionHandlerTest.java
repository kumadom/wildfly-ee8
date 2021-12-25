package com.example.app.com.jaxrs.exceptionmapper.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class AppBusinessExceptionHandlerTest {
	
	@Test
	public void hoge() {
		
		AppBusinessExceptionHandler handler = new AppBusinessExceptionHandler();
		RuntimeException ex = new RuntimeException();
		Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(null).build();
		
	}
	
	@Test
	public void fuga() throws ParseException {
		SimpleDateFormat form = new SimpleDateFormat("MM");
		Date hoge =form.parse("08");
		Date d = new Date("");
		System.out.println(hoge);
	}
}
