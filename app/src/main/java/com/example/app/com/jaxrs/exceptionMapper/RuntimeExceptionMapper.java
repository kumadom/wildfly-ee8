package com.example.app.com.jaxrs.exceptionMapper;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public Response toResponse(RuntimeException exception) {
		CommonErrorResponse res = new CommonErrorResponse();
		ErrorDetail detail = new ErrorDetail();
		detail.setErrorCode("SYSTEM ERROR");
		detail.setErrorMessage(exception.getMessage());
		res.setErrors(Arrays.asList(detail));
		logger.log(Level.SEVERE, exception.getMessage(), exception);
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
	}

}
