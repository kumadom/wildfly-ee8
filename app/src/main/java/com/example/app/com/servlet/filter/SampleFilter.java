package com.example.app.com.servlet.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;

public class SampleFilter implements Filter {

	private Logger logger;
	
	@Inject
	public SampleFilter(@LoggerName(LoggerNameValue.SYSTEM) Logger logger) {
		this.logger = logger;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("Servlet Filter!!!!!");
		HttpServletDumpWrapper wrapper = new HttpServletDumpWrapper((HttpServletRequest)request);
		chain.doFilter(wrapper, response);
	}

}
