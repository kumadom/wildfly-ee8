package com.example.app.com.core.log;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

@Dependent
public class LoggerFactory {

	@Produces
	@LoggerName(LoggerNameValue.DEFAULT)
	public Logger createLogger(InjectionPoint injectionPoint) {
		Logger logger = Logger.getLogger("LoggerFactory");
		logger.setLevel(Level.INFO);
		return logger;
	}

}
