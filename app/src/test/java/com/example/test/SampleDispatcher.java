package com.example.test;

import java.net.URISyntaxException;

import org.jboss.resteasy.core.SynchronousDispatcher;
import org.jboss.resteasy.core.providerfactory.ResteasyProviderFactoryImpl;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Test;

import com.example.app.com.jaxrs.exceptionmapper.NotFoundExceptionMapper;

public class SampleDispatcher {

	@Test
	public void test() throws URISyntaxException {
		SynchronousDispatcher fact = (SynchronousDispatcher) MockDispatcherFactory.createDispatcher();
		NotFoundExceptionMapper mapper = new NotFoundExceptionMapper();
		ResteasyProviderFactoryImpl provider = (ResteasyProviderFactoryImpl) fact.getProviderFactory();
		provider.registerProviderInstance(mapper);
		// Class clazz = NotFoundExceptionMapper.class;
		// SortedKey<ExceptionMapper> sort = new SortedKey<ExceptionMapper>(null,
		//		mapper, mapper.getClass(), false);
		// provider.getServerHelper().addExceptionMapper(ForbiddenExceptionMapper.class, false);
		// provider.getServerHelper().addExceptionMapper(NotFoundExceptionMapper.class, false);
		// provider.getServerHelper().getExceptionMappers().put(Types.getRawType(NotFoundException.class), sort);

		MockHttpRequest req = MockHttpRequest.post("/");
		MockHttpResponse res = new MockHttpResponse();
		fact.invoke(req, res);

		System.out.println(res.getStatus());
	}

}
