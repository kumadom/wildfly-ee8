package com.example.app;

import java.util.logging.Logger;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.jboss.resteasy.spi.metadata.ResourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.example.app.com.jaxrs.SampleResource;
import com.example.app.com.jaxrs.filter.CommonHeaderFilter;
import com.example.app.com.jaxrs.interceptor.CommonEntityInterceptor;
import com.example.app.com.jaxrs.interceptor.DspCompatibleInterceptor;
import com.example.app.com.jaxrs.request.model.ErrorResponse;
import com.example.app.rest.model.Hoge;
import com.example.app.rest.model.SampleRequest;
import com.example.test.RestClientTestBase;

public class SampleIT extends RestClientTestBase {

	@InjectMocks
	private CommonHeaderFilter filter;

	@Spy
	private Logger logger = Logger.getLogger("test");

	private AutoCloseable openMocks;
	private Dispatcher dispatcher;

	@InjectMocks
	private CommonEntityInterceptor commonEntityInterceptor;

	@InjectMocks
	private DspCompatibleInterceptor dspCompatibleInterceptor;
	
	@Before
	public void setUp() {
		// Filter処理のモックをMockitoを利用して生成
		openMocks = MockitoAnnotations.openMocks(this);
		// JAX-RSのモックをRESTEasyを利用して生成
		dispatcher = MockDispatcherFactory.createDispatcher();
		dispatcher.getProviderFactory().getContainerRequestFilterRegistry().registerSingleton(filter); // サーバーFilterの設定
		dispatcher.getProviderFactory().getServerReaderInterceptorRegistry().registerSingleton(commonEntityInterceptor);
		dispatcher.getProviderFactory().getServerWriterInterceptorRegistry().registerSingleton(dspCompatibleInterceptor);
		dispatcher.getRegistry().addResourceFactory(
				new POJOResourceFactory(new ResourceBuilder().buildRootResource(SampleResource.class).buildClass())); // リソースクラスの設定
	}

	@After
	public void tearDown() throws Exception {
		openMocks.close();
	}

	@Test
	public void hoge() throws Exception {

		MockHttpResponse response = new MockHttpResponse();

		Hoge hoge = new Hoge();
		hoge.setGooooal("goal");
		Jsonb jsonb = JsonbBuilder.create();
		String json = jsonb.toJson(hoge);

		dispatcher.invoke(MockHttpRequest.get("hoge").contentType(MediaType.APPLICATION_JSON).content(json.getBytes()),
				response);
		System.out.println(response.getContentAsString());
		System.out.println(response.getStatus());
	}

	@Test
	public void test001() {
		Response response = target.request().post(null);
		ErrorResponse err = response.readEntity(ErrorResponse.class);
	}

	@Test
	public void test() {
		ResteasyWebTarget target = client.target(resourcePath);
		CommonRequest req = new CommonRequest();
		req.setBusinessData("123456");
		req.setCommonData("commonData");
		BodyHeader header = new BodyHeader();
		header.setBodyHeader("bodyHeader");
		req.setHeader(header);
		System.out.println(resourcePath.toTemplate());
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

	private class CommonRequest {
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

	private class BodyHeader {
		private String bodyHeader;

		public String getBodyHeader() {
			return bodyHeader;
		}

		public void setBodyHeader(String bodyHeader) {
			this.bodyHeader = bodyHeader;
		}

	}

}
