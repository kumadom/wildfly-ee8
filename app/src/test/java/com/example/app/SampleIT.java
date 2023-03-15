package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.app.rest.FileController;
import com.example.app.rest.SampleController;
import com.example.app.rest.model.SampleModel;
import com.example.app.rest.model.SampleRequest;
import com.example.test.RestClientTestBase;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;

public class SampleIT extends RestClientTestBase {

	private static UriBuilder resourcePath;
	private static ResteasyWebTarget target;

	@BeforeClass
	public static void setUp() {
		resourcePath = UriBuilder.fromResource(SampleController.class).scheme("http").host("localhost").port(8080);
		target = client.target(resourcePath);
	}

	@Test
	public void sampleTest001() throws InterruptedException {
		Thread.sleep(15000);
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

	@Test
	public void sampleTest002() {
		UriBuilder path = UriBuilder.fromResource(FileController.class).scheme("http").host("localhost").port(8080);
		ResteasyWebTarget wt = client.target(path);

		MultipartFormDataOutput formOutputData = new MultipartFormDataOutput();
		formOutputData.addFormData("part1", (Object) "this is part 1", MediaType.TEXT_PLAIN_TYPE);
		formOutputData.addFormData("part2", "this is part 2", MediaType.TEXT_PLAIN_TYPE);

		GenericEntity<MultipartFormDataOutput> data = new GenericEntity<MultipartFormDataOutput>(formOutputData) {
		};

		// Response response = wt.request().build(null).invoke(null)
		// .post(Entity.entity(data, MediaType.MULTIPART_FORM_DATA_TYPE));
		MultipartFormDataInput multi = wt.request().buildPost(Entity.entity(data, MediaType.MULTIPART_FORM_DATA_TYPE))
				.invoke(MultipartFormDataInput.class);
		Map<String, List<InputPart>> hoge = multi.getFormDataMap();
		hoge.forEach((t, u) -> {
			try {
				System.out.println(u.get(0).getBodyAsString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		// Assertions.assertThat(response.getStatus()).isEqualTo(Status.OK.getStatusCode());

	}

}
