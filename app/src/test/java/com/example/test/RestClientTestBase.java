package com.example.test;

import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.BeforeClass;

import com.example.app.rest.SampleLocator;

public class RestClientTestBase {

	protected static ResteasyClient client = new ResteasyClientBuilder().build();
	protected static ResteasyWebTarget target;
	protected static UriBuilder resourcePath = UriBuilder.fromResource(SampleLocator.class).scheme("http").host("localhost")
			.port(8080);

	
	@BeforeClass
	public static void clientSetup() {
		System.out.println("RestClientTestBaseの初期化処理");
		// JAX-RSのクライアント機能をRESTEasyを利用して生成
		client.register(new AppTestRestClientFilter());
		target = client.target(resourcePath);

	}
	
}
