package com.example.app.rest.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

@Path("rest")
@ApplicationScoped
public class RestClientSample {

	@GET
	public void hoge() {
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHH");
		jakarta.ws.rs.core.Configuration conf = null;
		Client cl = ClientBuilder.newClient();
		//client.register(new SampleInterceptor());
		Configuration co = cl.getConfiguration();
		cl.register(new SampleInterceptor());
		
		Client client = ClientBuilder.newClient();
		//client.register(new SampleInterceptor());
		Configuration config = client.getConfiguration();
		Response res = client.target("https://reqres.in/api/users/2").request().get();
		Response re = cl.target("https://reqres.in/api/users/2").request().get();
		ResponseSample<SampleData> sample = res.readEntity(new GenericType<ResponseSample<SampleData>>() {});
		SampleData data = sample.getData();
		System.out.println(data.getAvatar());
		System.out.println(data.getId());
	}
	
}
