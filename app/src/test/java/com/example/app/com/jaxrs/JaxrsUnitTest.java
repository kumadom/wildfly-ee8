package com.example.app.com.jaxrs;

import java.util.logging.Logger;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.example.app.com.jaxrs.container.CommonHeaderFilter;
import com.example.app.com.jaxrs.exceptionmapper.ConstraintViolationExceptionMapper;
import com.example.app.com.jaxrs.exceptionmapper.RuntimeExceptionMapper;
import com.example.app.com.jaxrs.ext.CommonEntityInterceptor;
import com.example.app.com.jaxrs.ext.DspCompatibleInterceptor;
import com.example.app.rest.model.SampleModel;

public class JaxrsUnitTest {

	// JAX-RSのプロバイダー関連
	@InjectMocks private CommonHeaderFilter filter;
	@InjectMocks private CommonEntityInterceptor commonEntityInterceptor;
	@InjectMocks private DspCompatibleInterceptor dspCompatibleInterceptor;
	@InjectMocks private RuntimeExceptionMapper runtimeExceptionMapper;
	@InjectMocks private ConstraintViolationExceptionMapper constraintViolationExceptionMapper;
	
	// モック関連
	@Mock private Logger logger = Logger.getLogger("test");
	@Spy private Validator vaidator = Validation.buildDefaultValidatorFactory().getValidator();

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
		dispatcher.getProviderFactory().registerProviderInstance(constraintViolationExceptionMapper);
				
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

		SampleModel hoge = new SampleModel();
		hoge.setGooooal("goal");
		Jsonb jsonb = JsonbBuilder.create();
		String json = jsonb.toJson(hoge);

		dispatcher.invoke(MockHttpRequest.get("/hoge").contentType(MediaType.APPLICATION_JSON)
				.header("traceId", "00000000").content(json.getBytes()), response);
	}
	
}
