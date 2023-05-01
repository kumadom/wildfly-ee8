package com.example.app.com.jaxrs;

import com.example.app.rest.model.SampleModel;

import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("hoge")
public class SampleResource {

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public SampleModel hoge(@Valid SampleModel hoge) {
		return hoge;
	}

}
