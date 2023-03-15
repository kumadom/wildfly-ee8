package com.example.app.com.jaxrs.exceptionmapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;
import com.google.common.collect.Iterables;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	private final Pattern regex = Pattern.compile("[{}]");

	public ConstraintViolationExceptionMapper() {
	}

	@Inject
	public ConstraintViolationExceptionMapper(@LoggerName(LoggerNameValue.SYSTEM) Logger logger) {
		this.logger = logger;
	}

	@Context
	private HttpServletRequest req;

	private Logger logger;

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		logger.info("ConstraintViolationExceptionMapperの処理開始");
		logger.info(req.toString());
		try {
			byte[] bytes = IOUtils.toByteArray(req.getInputStream());
			logger.info(new String(bytes, StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		final List<ErrorDetailInfo> errors = exception.getConstraintViolations().stream().map(v -> {
			String errorCode = regex.matcher(v.getMessageTemplate()).replaceAll("");
			logger.info(v.getMessage());
			logger.info(Iterables.getLast(v.getPropertyPath()).getName());
			String message = MessageFormat.format(v.getMessage(), Iterables.getLast(v.getPropertyPath()));
			return new ErrorDetailInfo(errorCode, message);
		}).collect(Collectors.toList());

		return Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(new ErrorResponse(errors))
				.build();
	}

}
