package com.example.app.com.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@SampleInterceptorAnnotation
public class SampleInterceptors {

//	private final Logger logger = Logger.getLogger("Interceptor: %s", getClass().getName());
	
	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		
//		logger.fine("開始");
		System.out.println("SAMPLEinterceptor1開始");
		Object obj = ic.proceed();
		System.out.println("SAMPLEinterceptor1終了");
		return obj;
	}
	
}
