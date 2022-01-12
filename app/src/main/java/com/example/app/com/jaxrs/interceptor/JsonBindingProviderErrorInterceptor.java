package com.example.app.com.jaxrs.interceptor;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import com.example.app.com.core.exception.AppBusinessException;

@Provider
@Priority(value = Priorities.ENTITY_CODER)
public class JsonBindingProviderErrorInterceptor implements ReaderInterceptor {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		logger.info(MessageFormat.format("around reader interceptoir is started. class: {0}", getClass().getName()));
		
		try {
			Object obj = context.proceed();
			return obj;
		} catch (ProcessingException e) {
			logger.log(Level.INFO, e.getClass().getName());
			logger.log(Level.INFO, e.getClass().getCanonicalName());
			logger.log(Level.INFO, e.getClass().getSimpleName());
			logger.log(Level.INFO, e.toString());
			
			throw new AppBusinessException("APYC-00003", null);
		}
	}

}
