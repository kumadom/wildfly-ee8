package com.example.app.com.jaxrs.interceptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import org.apache.commons.io.IOUtils;

@Provider
@Priority(Priorities.USER)
public class CommonEntityInterceptor implements ReaderInterceptor {

	private final Logger logger = Logger.getLogger(getClass().getName());	

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		logger.info("ReaderInterceptorの開始");
		InputStream originalStream = context.getInputStream();
		byte[] bytes = IOUtils.toByteArray(originalStream);
		context.setInputStream(new ByteArrayInputStream(bytes));
		
		Jsonb jsonb = JsonbBuilder.create();
		CommonBody body = jsonb.fromJson(new ByteArrayInputStream(bytes), CommonBody.class);
		logger.info(new String(bytes, StandardCharsets.UTF_8));
		logger.info("ReaderInterceptorの終了");

		return context.proceed();
	}
	
}