package com.example.test;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.BeforeClass;

public class RestClientTestBase {

	protected static ResteasyClient client = new ResteasyClientBuilder().build();
	
	@BeforeClass
	public static void clientSetup() {
		System.out.println("RestClientTestBaseの初期化処理");
		// JAX-RSのクライアント機能をRESTEasyを利用して生成
		client.register(new AppTestRestClientFilter());
	}
	
}
