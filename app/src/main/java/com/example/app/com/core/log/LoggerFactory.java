package com.example.app.com.core.log;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@Dependent
public class LoggerFactory {

	@Produces
	@ApplicationLogger(LoggerNameValue.DEFAULT)
	public Logger createLogger(InjectionPoint injectionPoint) {
		ApplicationLogger annotation = injectionPoint.getAnnotated().getAnnotation(ApplicationLogger.class);
		System.out.println(annotation.value().name());
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

}
