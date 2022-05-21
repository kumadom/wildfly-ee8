package com.example.app.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.app.rest.model.SampleModel;

@ApplicationScoped
@Path("/patch")
@Consumes(MediaType.APPLICATION_JSON_PATCH_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PatchController {

	
	@PATCH
	public SampleModel patch(SampleModel model) {
		System.out.println("**************************");
		System.out.println("Welcome!!!PATCH");
		System.out.println(model.getGooooal());
		System.out.println("**************************");
		return model;
	}
	
	@GET
	public SampleModel get() {
		System.out.println("**************************");
		System.out.println("Welcome!!!GET");
		System.out.println("**************************");
		SampleModel model = new SampleModel();
		model.setGooooal("hoge");
		return model;
	}
	
}
