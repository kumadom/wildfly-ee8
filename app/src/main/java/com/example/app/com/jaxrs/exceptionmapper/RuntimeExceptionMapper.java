package com.example.app.com.jaxrs.exceptionmapper;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
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
