package com.example.app.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.ext.Providers;

@Path("exclude")
@ApplicationScoped
public class ExcludeInterceptorController {

	@Inject
	private ServletContext context;

	@Inject
	private Providers providers;

	@GET
	public void hoge() {

	}

}
