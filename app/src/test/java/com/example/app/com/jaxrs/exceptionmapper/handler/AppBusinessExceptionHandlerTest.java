package com.example.app.com.jaxrs.exceptionmapper.handler;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class AppBusinessExceptionHandlerTest {
	
	@Test
	public void hoge() {
		
		AppBusinessExceptionHandler handler = new AppBusinessExceptionHandler();
		Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(null).build();
		
	}
	
	@Test
	public void fuga() throws ParseException {
		String hoge = "ほげ";
		System.out.println(StringUtils.getBytes(hoge, StandardCharsets.UTF_8).length);
		System.out.println(new String(hoge.getBytes(StandardCharsets.UTF_8), 0, 3, StandardCharsets.UTF_8));
	}
}
