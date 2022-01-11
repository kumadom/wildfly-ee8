package com.example.app.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

@Path(value = "sample")
@ApplicationScoped
public class SampleLocator {

	@Inject
	private SampleController subResource;
	
	@Path(value = "")
	public SampleController findSubResource() {
		System.out.println("sub resource locator come!!!!");
		// throw new RuntimeException();
		return subResource;
	}
	
}
