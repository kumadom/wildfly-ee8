package com.example.app.com.jaxrs.container;

import java.io.IOException;
import java.util.logging.Logger;

import com.example.app.com.RequestContext;
import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.constants.AppPriorities;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(AppPriorities.FILTER.SWAGGER_HEADER_PROCESS)
public class SwaggerHeaderFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Inject private RequestContext con;
	
	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.fine("ContainerRequestFilterの開始");
		con.setStr("request");
		logger.fine("ContainerRequestFilterの終了");
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		logger.fine("ContainerResponseFilterの開始");
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,HEAD,OPTIONS");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type");
		logger.fine("ContainerResponseFilterの終了");
	}
	
}
