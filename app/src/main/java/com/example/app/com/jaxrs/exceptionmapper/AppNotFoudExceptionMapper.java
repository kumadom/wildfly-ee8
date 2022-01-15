package com.example.app.com.jaxrs.exceptionmapper;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.app.com.core.exception.AppNotFoundException;
import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.exceptionmapper.handler.AppBusinessExceptionHandler;

@Provider
public class AppNotFoudExceptionMapper implements ExceptionMapper<AppNotFoundException> {

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	@Inject private AppBusinessExceptionHandler handler;
	
	@Override
	public Response toResponse(AppNotFoundException exception) {
		logger.info("AppNotFoudExceptionMapperの処理開始");
		return handler.handle(exception, Status.NOT_FOUND);
	}

}
