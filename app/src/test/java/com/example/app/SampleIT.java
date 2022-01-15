package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.app.rest.SampleLocator;
import com.example.app.rest.model.SampleModel;
import com.example.app.rest.model.SampleRequest;
import com.example.test.RestClientTestBase;

public class SampleIT extends RestClientTestBase {

	private static UriBuilder resourcePath;
	private static ResteasyWebTarget target;

	@BeforeClass
	public static void setUp() {
		resourcePath = UriBuilder.fromResource(SampleLocator.class).scheme("http").host("localhost").port(8080);
		target = client.target(resourcePath);
	}

	@Test
	public void sampleTest001() {
		// テストデータの準備
		SampleRequest req = new SampleRequest();
		req.setCorrelationData("00001");
		SampleModel sampleModel = new SampleModel();
		sampleModel.setGooooal("goal");
		req.setSampleModel(sampleModel);
		Entity<SampleRequest> en = Entity.entity(req, MediaType.APPLICATION_JSON);
		
		// テストの実施
		Response res = target.request().header("Content-Type", MediaType.APPLICATION_JSON).post(en);
		
		// 結果の検証
		assertThat(res.getStatus()).isEqualTo(Status.OK.getStatusCode());
	}

}
