package com.example.app.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.app.com.interceptor.SampleInterceptorAnnotation;
import com.example.app.rest.model.SampleRequest;
import com.example.app.rest.model.SampleResponse;
import com.example.domain.service.SampleService;


@Path(value = "sample")
@ApplicationScoped
@SampleInterceptorAnnotation(eventName = "EVENT00001")
public class SampleController {
	
	@Inject
	private SampleService service;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Valid SampleRequest res) {
		System.out.println("Controller");
		service.persist();
		return Response.status(Status.OK).entity( new SampleResponse()).build();
	}
	
}
