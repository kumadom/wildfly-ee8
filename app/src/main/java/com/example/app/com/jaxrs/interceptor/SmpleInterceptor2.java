package com.example.app.com.jaxrs.interceptor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;

@Provider
@Priority(Priorities.ENTITY_CODER + 200)
public class SmpleInterceptor2 implements ReaderInterceptor, WriterInterceptor {

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;	

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		logger.log(Level.INFO, "SampleIntercptor2のreader処理");
		return context.proceed();
	}

	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		logger.log(Level.INFO, "SampleIntercptor2のwriter処理");		
		context.proceed();
	}

}
