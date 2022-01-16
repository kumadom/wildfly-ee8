package com.example.app.com.jaxrs.container;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import com.example.app.com.RequestContext;
import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.constants.AppPriorities;

@Provider
@Priority(AppPriorities.FILTER.SWAGGER_HEADER_PROCESS)
public class SwaggerHeaderFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Inject private RequestContext con;
	
	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("ContainerRequestFilterの開始");
		con.setStr("request");
		logger.info("ContainerRequestFilterの終了");
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		logger.info("ContainerResponseFilterの開始");
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,HEAD,OPTIONS");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type");
		logger.info("ContainerResponseFilterの終了");
	}
	
}
