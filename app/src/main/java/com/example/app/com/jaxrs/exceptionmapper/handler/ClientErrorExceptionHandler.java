package com.example.app.com.jaxrs.exceptionmapper.handler;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.core.message.MessageProperty;
import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;

@ApplicationScoped
public class ClientErrorExceptionHandler {

	@Inject
	@MessageProperty
	private ResourceBundle messages;
	
	@Inject @LoggerName(LoggerNameValue.SYSTEM) Logger logger = Logger.getLogger(getClass().getName());

	public Response handle(ClientErrorException exception, Status status) {
		logger.info(exception.getMessage());
		List<ErrorDetailInfo> errors = Arrays.asList(new ErrorDetailInfo("APYC-00001", messages.getString("APYC-00001")));
		return Response.status(status).entity(new ErrorResponse(errors)).type(MediaType.APPLICATION_JSON).build();
	}

}
