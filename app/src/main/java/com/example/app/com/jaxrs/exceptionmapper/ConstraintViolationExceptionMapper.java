package com.example.app.com.jaxrs.exceptionmapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	private final Pattern regex = Pattern.compile("[{}]");

	@Inject
	@LoggerName(LoggerNameValue.SYSTEM)
	private Logger logger;

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		logger.info("処理開始");
		
		exception.getConstraintViolations().stream().forEach(v -> logger.info(v.getMessage()));
		
		List<ErrorDetailInfo> errors = exception.getConstraintViolations().stream().map(v -> {
			return new ErrorDetailInfo(regex.matcher(v.getMessageTemplate()).replaceAll(""),
					MessageFormat.format(v.getMessage(),
							StreamSupport.stream(v.getPropertyPath().spliterator(), false)
									.filter(n -> ElementKind.PROPERTY == n.getKind()).map(p -> p.getName()).findFirst()
									.orElse("")));
		}).collect(Collectors.toList());

		return Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(new ErrorResponse(errors))
				.build();
	}

}
