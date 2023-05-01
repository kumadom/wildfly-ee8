package com.example.app.com.jaxrs.exceptionmapper;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;

	@Override
	public Response toResponse(RuntimeException exception) {
		logger.info("RuntimeExceptionMapperの処理開始");
		logger.log(Level.SEVERE, exception.getMessage(), exception);
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new ErrorResponse(Arrays.asList(new ErrorDetailInfo("SYSTEM ERROR", "システムエラーが発生しました。"))))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
