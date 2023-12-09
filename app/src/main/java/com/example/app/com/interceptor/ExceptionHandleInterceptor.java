package com.example.app.com.interceptor;

import com.example.app.com.interceptor.constants.InterceptorPriorities;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@Priority(InterceptorPriorities.APP_INFO_LOG_PROCESS)
@ExceptionHandling
public class ExceptionHandleInterceptor {

	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		try {
			return ic.proceed();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
