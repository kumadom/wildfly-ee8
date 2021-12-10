package com.example.app.rest;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.app.com.core.exception.AppBusinessException;
import com.example.app.com.interceptor.SampleInterceptorAnnotation;
import com.example.app.rest.model.SampleRequest;


@Path(value = "sample")
@ApplicationScoped
@SampleInterceptorAnnotation(eventName = "EVENT00001")
public class SampleController {
	
//	@Inject
//	private SampleService service;
	
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Valid SampleRequest res) {
		logger.info(Thread.currentThread().getName());
		throw new AppBusinessException("APYC-00001", new HashMap<String, String>(){
			private static final long serialVersionUID = 6831065768260095416L;
			{
				put("className", getClass().getName());
			}
		});
//		service.persist();
//		return Response.status(Status.OK).entity( new SampleResponse()).build();
	}
	
}
