package com.example.app.com.jaxrs.exceptionmapper.handler;

import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.app.com.core.exception.AppBusinessException;
import com.example.app.com.core.message.MessageProperty;
import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;

@ApplicationScoped
public class AppBusinessExceptionHandler {

	@Inject
	@MessageProperty
	private ResourceBundle messages;

	public Response handle(AppBusinessException exception, Status status) {
		return handle(exception, status, null);
	}

	public Response handle(AppBusinessException exception, Status status, List<String> errorInfo) {

		List<ErrorDetailInfo> errors = exception.getErrorDetails().stream()
				.map(e -> new ErrorDetailInfo(e.getCode(),
						MessageFormat.format(messages.getString(e.getCode()), (Object[])e.getArgs()), errorInfo))
				.collect(Collectors.toList());

		return Response.status(status).type(MediaType.APPLICATION_JSON).entity(new ErrorResponse(errors)).build();
	}

}
