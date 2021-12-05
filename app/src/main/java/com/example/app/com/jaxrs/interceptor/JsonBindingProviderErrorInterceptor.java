package com.example.app.com.jaxrs.interceptor;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import com.example.app.com.core.exception.BusinessException;

@Provider
@Produces("application/jsonb")
public class JsonBindingProviderErrorInterceptor implements ReaderInterceptor {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		logger.info(MessageFormat.format("around reader interceptoir is started. class: {0}", getClass().getName()));
		
		try {
			Object obj = context.proceed();
			return obj;
		} catch (ProcessingException e) {
			logger.log(Level.INFO, "JSONボディの解析中にエラーが発生", e);
			throw new BusinessException("API-00002", "hogehoge");
		}
	}

}
