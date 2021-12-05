package com.example.app.com.jaxrs.exceptionMapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.app.com.core.exception.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

	private final Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public Response toResponse(BusinessException exception) {
		logger.info(MessageFormat.format("{0}の処理を実行します。", exception.getClass().getName()));

		List<ErrorBody> errors = exception.getErrorDetails().stream()
				.map(e -> new ErrorBody(e.getCode(), MessageFormat.format(e.getCode(), (Object[]) e.getArgs())))
				.collect(Collectors.toList());

		return Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
				.entity(new CommonErrorResponse(errors)).build();
	}

}
