package com.example.app.com.jaxrs.exceptionmapper;

import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import com.example.app.com.core.exception.AppBusinessException;
import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.exceptionmapper.handler.AppBusinessExceptionHandler;

@Provider
public class AppBusinessExceptionMapper implements ExceptionMapper<AppBusinessException> {

	@Inject @LoggerName(LoggerNameValue.SYSTEM) Logger logger = Logger.getLogger(getClass().getName());

	@Inject AppBusinessExceptionHandler handler;
	
	@Override
	public Response toResponse(AppBusinessException exception) {
		logger.info("AppBusinessExceptionMapperの処理開始");
		return handler.handle(exception, Status.BAD_REQUEST);
	}

}
