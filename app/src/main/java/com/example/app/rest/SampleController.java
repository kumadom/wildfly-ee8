package com.example.app.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.app.com.interceptor.SampleInterceptorAnnotation;
import com.example.app.rest.model.SampleRequest;
import com.example.app.rest.model.SampleResponse;


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
		RuntimeException ex = null;
		logger.severe("nullnull");
		logger.log(Level.SEVERE, "nullのテスト", ex);
		logger.info(Thread.currentThread().getName());
		//throw new RuntimeException();
		// throw new AppBusinessException("APYC00004", null);
		
		// return null;
		//throw new RuntimeException();
//		service.persist();
		SampleResponse re = new SampleResponse();
		re.setHoge("");
		return Response.status(Status.OK).entity(re).build();
	}
	
}
