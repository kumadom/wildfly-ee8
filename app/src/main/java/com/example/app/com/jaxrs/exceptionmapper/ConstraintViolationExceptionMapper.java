package com.example.app.com.jaxrs.exceptionmapper;

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

import com.example.app.com.jaxrs.request.model.ErrorResponse;
import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public Response toResponse(ConstraintViolationException exception) {
		logger.info(MessageFormat.format("{0}の処理を実行します。", exception.getClass().getName()));
		List<ErrorDetailInfo> errors = exception.getConstraintViolations().stream().map(v -> {
			return new ErrorDetailInfo(v.getMessage(), StreamSupport.stream(v.getPropertyPath().spliterator(), false)
					.filter(n -> ElementKind.PROPERTY == n.getKind()).map(p -> (String) p.getName()).findFirst()
					.orElse(""));
		}).collect(Collectors.toList());
		
		return Response.status(Status.BAD_REQUEST).entity(new ErrorResponse(errors)).build();
	}

}
