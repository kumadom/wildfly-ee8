package com.example.app.rest;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.test.RestClientTestBase;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

public class ExcludeInterceptorControllerIT extends RestClientTestBase {

	private static UriBuilder resourcePath;
	private static ResteasyWebTarget target;

	@BeforeClass
	public static void setUp() {
		resourcePath = UriBuilder.fromResource(ExcludeInterceptorController.class).scheme("http").host("localhost")
				.port(8080);
		target = client.target(resourcePath);
	}

	@Test
	public void test() {
		Response res = target.request().header("Content-Type", MediaType.APPLICATION_JSON).get();

	}

}
