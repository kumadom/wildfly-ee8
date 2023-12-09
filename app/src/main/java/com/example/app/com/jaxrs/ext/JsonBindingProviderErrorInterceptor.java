package com.example.app.com.jaxrs.ext;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.app.com.core.exception.AppBusinessException;
import com.example.app.com.jaxrs.constants.AppPriorities;

import jakarta.annotation.Priority;
import jakarta.json.JsonException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;

@Provider
@Priority(AppPriorities.PRE_READ.JSON_BINDING_ERROR_PROCESS)
public class JsonBindingProviderErrorInterceptor implements ReaderInterceptor {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		logger.fine("JsonBindingProviderErrorInterceptorの処理開始");
		
		try {
			Object obj = context.proceed();
			return obj;
		} catch (ProcessingException | JsonException e) {
			logger.log(Level.FINE, e.getClass().getName());
			logger.log(Level.FINE, e.getClass().getCanonicalName());
			logger.log(Level.FINE, e.getClass().getSimpleName());
			logger.log(Level.FINE, e.toString());
			
			throw new AppBusinessException("APYC-00003", null);
		}
	}

}
