package com.example.app.com.jaxrs.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import com.example.app.com.RequestContext;

@Provider
public class CommonHeaderFilter implements ContainerRequestFilter {

	@Inject
	private RequestContext con;
	
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("ContainerRequestFilterの開始");
		con.setStr("request");
		logger.info("ContainerRequestFilterの終了");
	}
	
}
