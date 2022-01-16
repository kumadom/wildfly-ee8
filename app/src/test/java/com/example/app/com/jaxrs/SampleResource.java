package com.example.app.com.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.app.rest.model.SampleModel;

@Path("hoge")
public class SampleResource {

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public SampleModel hoge(SampleModel hoge) {
		return hoge;
	}

}