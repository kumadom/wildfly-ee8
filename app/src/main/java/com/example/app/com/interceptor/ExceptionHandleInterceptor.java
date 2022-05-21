package com.example.app.com.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.example.app.com.interceptor.constants.InterceptorPriorities;

@Interceptor
@Priority(InterceptorPriorities.APP_INFO_LOG_PROCESS)
@ExceptionHandling
public class ExceptionHandleInterceptor {

	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		try {
			System.out.println("START:ExceptionHandleInterceptor");
			return ic.proceed();
		} catch (Exception e) {
			System.out.println("END:ExceptionHandleInterceptor");
			e.printStackTrace();
			throw e;
		}
	}
}
