package com.example.app.com.jaxrs.exceptionmapper;

import java.util.Arrays;

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;

@Decorator
@Priority(Interceptor.Priority.APPLICATION)
public class DecoratorMapper implements ExceptionMapper<ConstraintViolationException> {

	@Inject
	@Delegate
	private ExceptionMapper<ConstraintViolationException> mapper;
	
	@Override
	public Response toResponse(ConstraintViolationException exception) {
		Response res = mapper.toResponse(exception);
		ErrorResponse response = (ErrorResponse)res.getEntity();
		response.setErrorDetailInfo(Arrays.asList(new ErrorDetailInfo("SYSTEM ERROR", "フハハ")));
		System.out.println("sssssssssssssssssssssssssssssssssssssssss");
		return res;
	}

}
