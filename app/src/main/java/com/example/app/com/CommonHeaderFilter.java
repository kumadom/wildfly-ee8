package com.example.app.com;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CommonHeaderFilter implements ContainerRequestFilter {

	@Inject
	private RequestContext con;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("リクエストフィルタの開始");
		System.out.println(requestContext.toString());
		System.out.println(requestContext.getHeaders());
		con.setStr("request");
		System.out.println(requestContext.getHeaderString("Content-Type"));
		System.out.println(requestContext.getHeaderString("hoge"));
		System.out.println("リクエストフィルタの終了");
	}
	
}
