package com.example.app.rest.resource;

import com.example.app.rest.client.ExtGenericRestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/typeerasure")
@ApplicationScoped
public class TypeErasureResource {

	@Inject
//	private Client client;
//	private GenericRestClient<ParentResponse<ChildResponse>> client;
	private ExtGenericRestClient client;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Valid
	public Object endpoint() {
		ParentResponse<ChildResponse> re = client.execute();
		System.out.println(re.getData().getId());
//		System.out.println("START!!!　CHECK");
//		Response res = client.target("https://reqres.in/api/users/2").request().get();
//		res.bufferEntity();
//		System.out.println(res.readEntity(String.class));
//		GenericType<ParentResponse<ChildResponse>>genericType = new GenericType<ParentResponse<ChildResponse>>(){};
//		ParentResponse<ChildResponse> re = res.readEntity(genericType);
//		System.out.println(re.getData().getId());
		
		return re;
	}

	
}
