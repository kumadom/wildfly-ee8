package com.example.app.com.jaxrs.exceptionMapper;

import java.util.Arrays;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

	@Override
	public Response toResponse(RuntimeException exception) {
		CommonErrorResponse res = new CommonErrorResponse();
		ErrorDetail detail = new ErrorDetail();
		detail.setErrorCode("SYSTEM ERROR");
		detail.setErrorMessage(exception.getMessage());
		res.setErrors(Arrays.asList(detail));
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
	}

}
