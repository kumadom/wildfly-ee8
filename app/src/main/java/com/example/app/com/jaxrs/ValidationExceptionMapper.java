package com.example.app.com.jaxrs;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		List<ErrorDetail> names = exception.getConstraintViolations().stream().map(v -> {
			return new ErrorDetail(v.getMessage(), StreamSupport.stream(v.getPropertyPath().spliterator(), false)
					.filter(n -> ElementKind.PROPERTY == n.getKind()).map(p -> (String) p.getName()).findFirst()
					.orElse(""));
		}).collect(Collectors.toList());
		names.forEach(System.out::println);
		CommonErrorResponse res = new CommonErrorResponse();
		res.setErrors(names);
		return Response.status(Status.BAD_REQUEST).entity(res).build();
	}

}
