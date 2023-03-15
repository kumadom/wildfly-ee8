package com.example.app.com.jaxrs.exceptionmapper;

import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.exceptionmapper.handler.ClientErrorExceptionHandler;

@Provider
public class ClientErrorExceptionMapper implements ExceptionMapper<ClientErrorException> {

	@Inject private ClientErrorExceptionHandler handler;
	
	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;

	@Override
	public Response toResponse(ClientErrorException exception) {
		logger.info("ClientErrorExceptionMapperの処理開始");
		return handler.handle(exception, Status.BAD_GATEWAY);
	}
	
}
