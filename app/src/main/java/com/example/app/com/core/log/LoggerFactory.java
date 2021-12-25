package com.example.app.com.core.log;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@Dependent
public class LoggerFactory {

	@Produces
	@LoggerName(LoggerNameValue.DEFAULT)
	public Logger createLogger(InjectionPoint injectionPoint) {
		Logger logger = Logger.getLogger("LoggerFactory");
		logger.setLevel(Level.FINE);
		return logger;
	}

}
