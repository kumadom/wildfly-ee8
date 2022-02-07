package com.example.app.com.jaxrs.ext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import org.apache.commons.io.IOUtils;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.constants.AppPriorities;

@Provider
@Priority(AppPriorities.PRE_READ.COMMON_BODY_PROCESS)
public class CommonEntityInterceptor implements ReaderInterceptor {

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	@Context
	private Providers ss;
	
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		logger.info("CommonEntityInterceptorの開始");

		logger.info(ss.toString());
		try {
			Jsonb b = ss.getContextResolver(Jsonb.class, MediaType.APPLICATION_JSON_TYPE).getContext(null);
			if(b != null) logger.info(b.toString());
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		InputStream originalStream = context.getInputStream();
		logger.info(originalStream.toString());
		byte[] bytes = IOUtils.toByteArray(originalStream);
		logger.info("hoge");
		JsonReader reader = Json.createReader(new ByteArrayInputStream(bytes));
		JsonObject obj = reader.readObject();
		JsonValue v = obj.get("appRequest");
		if (v == null) logger.info("Null");
		JsonValue appRequest = Optional.ofNullable(v).orElse(obj);
		context.setInputStream(new ByteArrayInputStream(appRequest.toString().getBytes(StandardCharsets.UTF_8)));
//		logger.info(IOUtils.toString(context.getInputStream(), StandardCharsets.UTF_8));
//		logger.info(IOUtils.toString(context.getInputStream(), StandardCharsets.UTF_8));
//		logger.info(IOUtils.toString(context.getInputStream(), StandardCharsets.UTF_8));
		logger.info(appRequest.toString());
		logger.info("CommonEntityInterceptorの終了");

		return context.proceed();
	}
	
}
