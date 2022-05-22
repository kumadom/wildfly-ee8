package com.example.app.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.app.soa.SoaSample;

@ApplicationScoped
@Path(value = "soa")
public class SoaController {

	@Inject
	private SoaSample sample;
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void sample() {
		sample.sample();
	}
	
}
