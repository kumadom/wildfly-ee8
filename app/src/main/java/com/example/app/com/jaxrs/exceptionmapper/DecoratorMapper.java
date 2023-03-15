package com.example.app.com.jaxrs.exceptionmapper;

import java.util.Arrays;

import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;

import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptor;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

@Decorator
@Priority(Interceptor.Priority.APPLICATION)
public class DecoratorMapper implements ExceptionMapper<ConstraintViolationException> {

	@Inject
	@Delegate
	private ExceptionMapper<ConstraintViolationException> mapper;

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		Response res = mapper.toResponse(exception);
		ErrorResponse response = (ErrorResponse) res.getEntity();
		response.setErrorDetailInfo(Arrays.asList(new ErrorDetailInfo("SYSTEM ERROR", "フハハ")));
		System.out.println("sssssssssssssssssssssssssssssssssssssssss");
		return res;
	}

}
