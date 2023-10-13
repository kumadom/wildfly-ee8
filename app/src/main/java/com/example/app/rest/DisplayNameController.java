package com.example.app.rest;

import com.example.app.rest.model.DisplayNameRequest;
import com.example.app.rest.model.DisplayNameResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/displayName")
@ApplicationScoped
public class DisplayNameController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Valid
	public DisplayNameResponse displaySample(DisplayNameRequest req) {
		DisplayNameResponse res = new DisplayNameResponse();
		res.setValue(req.getValue());
		
		return res;
	}

}
