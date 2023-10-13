package com.example.app.com.jaxrs.exceptionmapper;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	// @Inject ClientErrorExceptionHandler handler;
	
	// @Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;

	@Override
	public Response toResponse(NotFoundException exception) {
		// logger.info("NotFoundExceptionMapperの処理開始");
		return Response.status(404).build();
	}

}
