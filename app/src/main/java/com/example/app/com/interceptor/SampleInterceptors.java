package com.example.app.com.interceptor;

import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@SampleInterceptorAnnotation(eventName = "")
public class SampleInterceptors {

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		logger.info("リソースクラスのインターセプターを開始");
		Object obj = ic.proceed();
		logger.info("リソースクラスのインターセプターを終了");
		return obj;
	}
	
}
