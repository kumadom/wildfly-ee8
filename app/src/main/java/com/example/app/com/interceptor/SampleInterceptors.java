package com.example.app.com.interceptor;

import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@SampleInterceptorAnnotation(eventName = "")
public class SampleInterceptors {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		logger.info("リソースクラスのインターセプターを開始");
		Object obj = ic.proceed();
		logger.info("リソースクラスのインターセプターを終了");
		return obj;
	}
	
}
