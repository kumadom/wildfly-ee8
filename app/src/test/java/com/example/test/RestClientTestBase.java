package com.example.test;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.junit.BeforeClass;

public class RestClientTestBase {

	protected static ResteasyClient client = new ResteasyClientBuilderImpl().build();
	
	@BeforeClass
	public static void clientSetup() {
		// JAX-RSのクライアント機能をRESTEasyを利用して生成
		client.register(new AppTestRestClientFilter());
	}
	
}
