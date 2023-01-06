package com.example.app;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.Providers;

import org.assertj.core.api.Assertions;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.MethodRule;

import com.example.app.com.core.log.LoggerFactory;
import com.example.app.com.json.bind.adapter.CustomJsonAdaptor;
import com.example.app.rest.SampleController;
import com.example.app.rest.model.SampleModel;
import com.example.app.rest.model.SampleRequest;
import com.example.domain.service.SampleService;
import com.example.test.RestClientTestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeldSampleTest extends RestClientTestBase {

	private static UriBuilder resourcePath;
	private static ResteasyWebTarget target;
	
	static {
		Logger.getLogger("org.jboss.weld").setLevel(Level.WARNING);
	}
	
	@BeforeClass
	public static void classInit() {
		resourcePath = UriBuilder.fromResource(SampleController.class).scheme("http").host("localhost").port(8080);
		target = client.target(resourcePath);
	}
	
	@ClassRule
	public static WeldInitiator weld = WeldInitiator.from(SampleController.class, SampleService.class, LoggerFactory.class, Providers.class).build();
	
	@Rule
	public MethodRule rule = weld.getTestClassInjectorRule();
	
	@Inject
	private SampleController controller;
	
	private Dispatcher dispatcher;
	
	
	// JsonBindingProvider provider;
	
	@Before
	public void init() {
		dispatcher = MockDispatcherFactory.createDispatcher();
		dispatcher.getRegistry().addSingletonResource(controller);
		
	}
	
	public void test() throws JsonProcessingException, URISyntaxException, UnsupportedEncodingException {
		SampleRequest req = new SampleRequest();
		req.setCorrelationData("00001");
		SampleModel sampleModel = new SampleModel();
		sampleModel.setGooooal("goal");
		req.setSampleModel(sampleModel);

		dispatcher.getProviderFactory().getProviderInstances().forEach(o -> System.out.println(o.getClass().toString()));
		// dispatcher.getProviderFactory().getProviderInstances().stream().filter(e -> e instanceof JsonBindingProvider).forEach(e -> System.out.println(e.toString()));
		//dispatcher.getProviderFactory().getProviderClasses().forEach(e -> System.out.println(e.toString()));
		//ContextResolver<Jsonb> contextResolver = providers.getContextResolver(Jsonb.class, MediaType.APPLICATION_JSON_TYPE);
		
		Logger logger = Logger.getAnonymousLogger();
		logger.setLevel(Level.INFO);
		CustomJsonAdaptor adapt = new CustomJsonAdaptor(logger);
		
		String str = JsonbBuilder.create(new JsonbConfig().withAdapters(adapt)).toJson(req);
		System.out.println(str);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(req));
		MockHttpRequest request = MockHttpRequest.post("sample").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(req).getBytes());
		MockHttpResponse response = new MockHttpResponse();
		
		// テストの実施
		dispatcher.invoke(request, response);
		System.out.println(response.getContentAsString());
		Assertions.assertThat(response.getStatus()).isEqualTo(Status.OK.getStatusCode());
		
	}
	
}
