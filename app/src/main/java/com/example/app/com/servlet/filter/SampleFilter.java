package com.example.app.com.servlet.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;

import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class SampleFilter implements Filter {

	private Logger logger;
	
	@Inject
	public SampleFilter(@LoggerName(LoggerNameValue.SYSTEM) Logger logger) {
		this.logger = logger;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.fine("Servlet Filter!!!!!");

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		Enumeration<String>headerNames = httpRequest.getHeaderNames();
	    HttpServletDumpWrapper wrapper = new HttpServletDumpWrapper((HttpServletRequest)request);
		chain.doFilter(wrapper, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
