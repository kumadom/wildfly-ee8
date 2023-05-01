package com.example.app.com.jaxrs.exceptionmapper;

import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

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
