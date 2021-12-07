package com.example.app.com.jaxrs.exceptionmapper.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.example.app.com.core.exception.BusinessException;
import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;

@Provider
public class BusinessExceptionHandler {

	public Response handle(BusinessException exception, Status status) {
		return handle(exception, status, null);
	}
	
	public Response handle(BusinessException exception, Status status, List<String> errorInfo) {
		List<ErrorDetailInfo> errors = exception.getErrorDetails().stream()
				.map(e -> new ErrorDetailInfo(e.getCode(), "hogehoge", errorInfo)).collect(Collectors.toList());

		return Response.status(status).type(MediaType.APPLICATION_JSON).entity(new ErrorResponse(errors)).build();
	}

}
