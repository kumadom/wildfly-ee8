package com.example.app.com.jaxrs.exceptionMapper;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

	private final Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public Response toResponse(RuntimeException exception) {
		logger.log(Level.SEVERE, exception.getMessage(), exception);
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new CommonErrorResponse(Arrays.asList(new ErrorBody("SYSTEM ERRPR", "システムエラーが発生しました。"))))
				.type(MediaType.APPLICATION_JSON).build();
	}

}
