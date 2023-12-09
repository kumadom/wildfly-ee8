package com.example.app.com.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.logging.Logger;

import com.example.app.com.core.annotation.Masking;
import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.interceptor.constants.InterceptorPriorities;
import com.example.app.com.json.bind.AppJsonConfig;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.json.bind.Jsonb;

@Interceptor
@Priority(InterceptorPriorities.APP_INFO_LOG_PROCESS)
@SampleInterceptorAnnotation(eventName = "")
public class SampleInterceptors {

	private Logger logger;
	
	private Jsonb jsonb;
	
	@Inject
	public SampleInterceptors(@LoggerName(LoggerNameValue.SYSTEM) Logger logger) {
		this.logger = logger;
		this.jsonb = new AppJsonConfig().getContext(null);
	}
	
	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		logger.fine("リソースクラスのインターセプターを開始");
		
		Method method = ic.getMethod();
		logger.fine(ic.getMethod().getName());
		for(Parameter param: method.getParameters()) {
			if(param.isAnnotationPresent(Masking.class)) {
				logger.fine("入力情報のマスク化情報を出力します");
				Class<?> clazz = param.getType();
				for(Object arg: ic.getParameters()) {
					if(clazz.isInstance(arg)) {
						logger.fine(jsonb.toJson(arg));
						// 同じインスタンスが設定されていた場合、同じものが出力されるため、1回出力したら処理を中断する。
						// なお、@Maskingが同一のオブジェクト型に複数回設定されていた場合は2回出力される。
						break;
					}
				}
			}
		}

		
		Object obj;
		try {
			obj = ic.proceed();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		logger.info("リソースクラスのインターセプターを終了");
		return obj;
	}
	
}
