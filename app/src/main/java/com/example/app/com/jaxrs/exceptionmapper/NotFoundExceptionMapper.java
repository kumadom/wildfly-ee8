package com.example.app.com.jaxrs.exceptionmapper;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.exceptionmapper.handler.ClientErrorExceptionHandler;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Inject ClientErrorExceptionHandler handler;
	
	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;

	@Override
	public Response toResponse(NotFoundException exception) {
		logger.info("NotFoundExceptionMapperの処理開始");
		return handler.handle(exception, Status.NOT_FOUND);
	}

}
