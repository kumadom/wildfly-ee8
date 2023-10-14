package com.example.app.rest.client;

import java.io.IOException;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;

public class SampleInterceptor implements ClientRequestFilter, WriterInterceptor, ReaderInterceptor, ClientResponseFilter {

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("filter");
	}

	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		System.out.println("aroundWriteTo");
	}

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		System.out.println("aroundReadFrom");
		Object hoge = context.proceed();
		return hoge;
	}

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("filter2");
	}

}
