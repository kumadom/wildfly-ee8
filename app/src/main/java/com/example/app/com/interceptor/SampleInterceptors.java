package com.example.app.com.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@SampleInterceptorAnnotation(eventName = "")
public class SampleInterceptors {

	private Logger logger;
	// @Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	@Inject
	public SampleInterceptors(@LoggerName(LoggerNameValue.SYSTEM) Logger logger) {
		this.logger = logger;
	}
	
	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		logger.info("リソースクラスのインターセプターを開始");
		Method method = ic.getMethod();
		logger.info(ic.getMethod().getName());
		Arrays.stream(method.getParameters()).forEach(p -> {
			logger.info(p.getType().getName());
		});
		
		
		for(Object parameter : ic.getParameters()) {
			Jsonb jsonb = JsonbBuilder.create();
			logger.info(jsonb.toJson(ic.getParameters()[0]));
		}
		
		if (ic.getParameters().length > 0) {
			Jsonb jsonb = JsonbBuilder.create();
			logger.info(jsonb.toJson(ic.getParameters()[0]));
			logger.info(String.valueOf(Thread.currentThread().getContextClassLoader().hashCode()));
		}
		Object obj = ic.proceed();
		logger.info("リソースクラスのインターセプターを終了");
		return obj;
	}
	
}
