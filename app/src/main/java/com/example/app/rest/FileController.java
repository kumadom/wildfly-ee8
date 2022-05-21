package com.example.app.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
