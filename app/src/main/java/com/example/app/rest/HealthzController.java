package com.example.app.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.app.com.interceptor.SampleInterceptorAnnotation;

@ApplicationScoped
@SampleInterceptorAnnotation(eventName = "EVENT00002")
@Path(value = "healthz")
public class HealthzController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response healthz() {
		return Response.status(Status.OK).build();
	}
	
}
