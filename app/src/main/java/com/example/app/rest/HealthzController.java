package com.example.app.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import com.example.app.com.interceptor.SampleInterceptorAnnotation;

@ApplicationScoped
@SampleInterceptorAnnotation(eventName = "EVENT00002")
@Path("/")
public class HealthzController {
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "healthz")
	public String healthz() {
		return "string";
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "healtha")
	public String healtha() {
		return "string";
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "healthb")
	public Response healthb() {
		return Response.status(Status.OK).entity("Strgin").build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "healthc")
	public Response healthc() {
		return Response.status(Status.OK).entity("Strgin").build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "healthd")
	public Response healthd() {
		return Response.status(Status.OK).entity("Strgin").build();
	}
	
}
