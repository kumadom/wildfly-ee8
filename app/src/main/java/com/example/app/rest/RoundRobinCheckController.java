package com.example.app.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

@Path("check")
@ApplicationScoped
public class RoundRobinCheckController extends DecoratorAbstract implements DecoratorInterface {

	@GET
	public void get() {
		Client client = ClientBuilder.newBuilder().build();
		System.out.println("START!!!　CHECK");
		client.target("http://roundrobin-service.sample.svc.cluster.local/exclude").request().get();
		
	}
	
}
