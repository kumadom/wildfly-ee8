package com.example.app.rest;

import com.example.app.com.interceptor.SampleInterceptorAnnotation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@SampleInterceptorAnnotation(eventName = "EVENT00002")
@Path("/karamoji")
public class KaramojiController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void test(@Valid KaramojiSample sample) {
		System.out.println("*****************************");
		System.out.println("**********hoge**********");
		System.out.println("*****************************");
	}
	
}
