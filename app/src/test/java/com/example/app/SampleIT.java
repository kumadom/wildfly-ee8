package com.example.app;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Test;

import com.example.app.rest.SampleController;
import com.example.app.rest.model.SampleRequest;

public class SampleIT {

	private ResteasyClient client = new ResteasyClientBuilder().build();
	private UriBuilder resourcePath = UriBuilder.fromResource(SampleController.class).scheme("http").host("localhost").port(8080);
	
	@Test
	public void test() {
		ResteasyWebTarget target = client.target(resourcePath);
		CommonRequest req = new CommonRequest();
		req.setBusinessData("123456");
		req.setCommonData("commonData");
		BodyHeader header = new BodyHeader();
		header.setBodyHeader("bodyHeader");
		req.setHeader(header);
		Entity<CommonRequest> en = Entity.entity(req, MediaType.APPLICATION_JSON);
		Response res = target.request().header("Content-Type", MediaType.APPLICATION_JSON).post(en);
		System.out.println(res.getEntity());
	}
	
	@Test
	public void tests() {
		ResteasyWebTarget target = client.target(resourcePath);
		SampleRequest req = new SampleRequest();
		req.setBusinessData("123456");
		req.setCorrelationData("hoge");
		Entity<SampleRequest> en = Entity.entity(req, MediaType.APPLICATION_JSON);
		Response res = target.request().header("Content-Type", MediaType.APPLICATION_JSON).post(en);
		System.out.println(res.getEntity());
		
	}
	
	private class CommonRequest{
		private String businessData;
		private String commonData;
		private BodyHeader header;
		public String getBusinessData() {
			return businessData;
		}
		public void setBusinessData(String businessData) {
			this.businessData = businessData;
		}
		public String getCommonData() {
			return commonData;
		}
		public void setCommonData(String commonData) {
			this.commonData = commonData;
		}
		public BodyHeader getHeader() {
			return header;
		}
		public void setHeader(BodyHeader header) {
			this.header = header;
		}
	}
	
	private class BodyHeader{
		private String bodyHeader;

		public String getBodyHeader() {
			return bodyHeader;
		}

		public void setBodyHeader(String bodyHeader) {
			this.bodyHeader = bodyHeader;
		}
		
	}
}
