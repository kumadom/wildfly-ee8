package com.example.app.com.jaxrs.ext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;

import org.apache.commons.io.IOUtils;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.constants.AppPriorities;

@Provider
@Priority(AppPriorities.PRE_READ.COMMON_BODY_PROCESS)
public class CommonEntityInterceptor implements ReaderInterceptor {

	@Inject
	@LoggerName(LoggerNameValue.SYSTEM)
	private Logger logger;
	
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		logger.info("CommonEntityInterceptorの開始");
		logger.info(context.toString());
		InputStream originalStream = context.getInputStream();
		logger.info(originalStream.toString());
		byte[] bytes = IOUtils.toByteArray(originalStream);
		logger.info("hoge");
		logger.info(new String(bytes, StandardCharsets.UTF_8));
		if(MediaType.APPLICATION_JSON_TYPE.equals(context.getMediaType())) {
			JsonReader reader = Json.createReader(new ByteArrayInputStream(bytes));
			JsonObject obj = reader.readObject();
			JsonValue v = obj.get("appRequest");
			if (v == null) logger.info("Null");
			JsonValue appRequest = Optional.ofNullable(v).orElse(obj);
			context.setInputStream(new ByteArrayInputStream(appRequest.toString().getBytes(StandardCharsets.UTF_8)));
			logger.info(appRequest.toString());
		}else {
			context.setInputStream(new ByteArrayInputStream(bytes));
		}
		logger.info("CommonEntityInterceptorの終了");

		return context.proceed();
	}
	
}
