package com.example.app.com.jaxrs.exceptionMapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public Response toResponse(ConstraintViolationException exception) {
		logger.info(MessageFormat.format("{0}の処理を実行します。", exception.getClass().getName()));
		List<ErrorBody> errors = exception.getConstraintViolations().stream().map(v -> {
			return new ErrorBody(v.getMessage(), StreamSupport.stream(v.getPropertyPath().spliterator(), false)
					.filter(n -> ElementKind.PROPERTY == n.getKind()).map(p -> (String) p.getName()).findFirst()
					.orElse(""));
		}).collect(Collectors.toList());
		
		return Response.status(Status.BAD_REQUEST).entity(new CommonErrorResponse(errors)).build();
	}

}
