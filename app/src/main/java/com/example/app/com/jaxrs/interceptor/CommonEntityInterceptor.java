package com.example.app.com.jaxrs.interceptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import org.apache.commons.io.IOUtils;

import com.example.app.com.core.exception.AppBusinessException;

@Provider
@Priority(Priorities.USER)
public class CommonEntityInterceptor implements ReaderInterceptor {

	private final Logger logger = Logger.getLogger(getClass().getName());	

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		logger.info("ReaderInterceptorの開始");

		InputStream originalStream = context.getInputStream();
		byte[] bytes = IOUtils.toByteArray(originalStream);
		
		JsonReader reader = Json.createReader(new ByteArrayInputStream(bytes));
		JsonObject obj = reader.readObject();
		JsonValue v = obj.get("appRequest");
		if (v == null) logger.info("Null");
		JsonValue appRequest = Optional.ofNullable(v).orElseThrow(() -> new AppBusinessException("APYC00006", null));
		context.setInputStream(new ByteArrayInputStream(appRequest.toString().getBytes(StandardCharsets.UTF_8)));

		logger.info(appRequest.toString());
		logger.info("ReaderInterceptorの終了");

		return context.proceed();
	}
	
}
