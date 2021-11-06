package com.example.app.rest;

import javax.enterprise.context.ApplicationScoped;
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

@Path(value = "sample")
@ApplicationScoped
@SampleInterceptorAnnotation
public class SampleController {

	public SampleController() {
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Valid SampleRequest res) {
		System.out.println("Controller");
		// Logger.getLogger("", "");
		System.out.println(res.getBusinessData());
		return Response.status(Status.OK).entity( new SampleResponse()).build();
	}
	
}
