package com.example.app.sample;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/abst")
@ApplicationScoped
public class SampleResource {

	@Inject
	private SampleService service;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response si() {
		service.aa();
		System.out.println(SampleConst.PropertyKey.HOGE.replace(SampleConst.PropertyKey.FQN_PLACEHOLDER,
				this.getClass().getName()));
		return Response.ok().build();
	}

}
