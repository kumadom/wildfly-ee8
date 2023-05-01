package com.example.app.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;

@Path("/file")
@ApplicationScoped
public class FileController {

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	//@Produces(MediaType.APPLICATION_JSON)
	public MultipartFormDataOutput file(MultipartFormDataInput input) {
//		System.out.println("hogehogehgoe");
//		return Response.ok().entity(new Object()).build();
		MultipartFormDataOutput out = new MultipartFormDataOutput();
		out.addFormData("part1", (Object) "this is part 1", MediaType.TEXT_PLAIN_TYPE);
		out.addFormData("part2", "this is part 2", MediaType.TEXT_PLAIN_TYPE);
		//return Response.ok(out).header("content-disposition", "attachment; filename=\"temp.csv\"").build();
		return out;
	}
	
}
