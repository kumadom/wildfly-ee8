package com.example.app.com.jaxrs;

import java.util.logging.Logger;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.example.app.com.jaxrs.exceptionmapper.RuntimeExceptionMapper;
import com.example.app.com.jaxrs.filter.CommonHeaderFilter;
import com.example.app.com.jaxrs.interceptor.CommonEntityInterceptor;
import com.example.app.com.jaxrs.interceptor.DspCompatibleInterceptor;
import com.example.app.rest.model.Hoge;

public class JaxrsUnitTest {

	// JAX-RSのプロバイダー関連
	@InjectMocks private CommonHeaderFilter filter;
	@InjectMocks private CommonEntityInterceptor commonEntityInterceptor;
	@InjectMocks private DspCompatibleInterceptor dspCompatibleInterceptor;
	@InjectMocks private RuntimeExceptionMapper runtimeExceptionMapper;
	
	// モック関連
	@Spy private Logger logger = Logger.getLogger("test");

	private AutoCloseable openMocks;
	private Dispatcher dispatcher;
	
	@Before
	public void setUp() {
		// Filter処理のモックをMockitoを利用して生成
		openMocks = MockitoAnnotations.openMocks(this);
		// JAX-RSのモックをRESTEasyを利用して生成
		dispatcher = MockDispatcherFactory.createDispatcher();
		dispatcher.getProviderFactory().getContainerRequestFilterRegistry().registerSingleton(filter); // サーバーFilterの設定
		dispatcher.getProviderFactory().getServerReaderInterceptorRegistry().registerSingleton(commonEntityInterceptor);
		dispatcher.getProviderFactory().getServerWriterInterceptorRegistry().registerSingleton(dspCompatibleInterceptor);
		dispatcher.getProviderFactory().registerProviderInstance(runtimeExceptionMapper);
				
		SampleResource resource = new SampleResource();
		dispatcher.getRegistry().addSingletonResource(resource, "/");
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

		dispatcher.invoke(MockHttpRequest.get("/hoge").contentType(MediaType.APPLICATION_JSON).content(json.getBytes()),
				response);
		System.out.println(response.getContentAsString());
		System.out.println(response.getStatus());
	}
	
}
