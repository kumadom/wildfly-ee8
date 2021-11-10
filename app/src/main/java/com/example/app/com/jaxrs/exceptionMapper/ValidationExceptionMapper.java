package com.example.app.com.jaxrs.exceptionMapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	private final Pattern regex = Pattern.compile("[{}]");
	
	@Override
	public Response toResponse(ConstraintViolationException exception) {
		logger.log(Level.FINE, exception.getMessage(), exception);
		List<ErrorDetail> names = exception.getConstraintViolations().stream().map(v -> {
			return new ErrorDetail(v.getMessage(), StreamSupport.stream(v.getPropertyPath().spliterator(), false)
					.filter(n -> ElementKind.PROPERTY == n.getKind()).map(p -> (String) p.getName()).findFirst()
					.orElse(""));
		}).collect(Collectors.toList());
		exception.getConstraintViolations().stream().forEach(v -> {
			String prop = StreamSupport.stream(v.getPropertyPath().spliterator(), false)
					.filter(n -> ElementKind.PROPERTY == n.getKind())
					.map(p -> p.getName()).findFirst().orElse("");
			logger.info(MessageFormat.format(v.getMessage(), prop));
			logger.info(regex.matcher(v.getMessageTemplate()).replaceAll(""));
		});
		
		
		CommonErrorResponse res = new CommonErrorResponse();
		res.setErrors(names);
		return Response.status(Status.BAD_REQUEST).entity(res).build();
	}

}
