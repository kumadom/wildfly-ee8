package com.example.app.com.json.bind.adapter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.json.bind.adapter.JsonbAdapter;

import org.apache.commons.lang3.StringUtils;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;

public class CustomJsonAdaptor implements JsonbAdapter<String, String> {

	@Inject @LoggerName(LoggerNameValue.SYSTEM) Logger logger;
	
	@Override
	public String adaptToJson(String obj) throws Exception {
		logger.severe("OOOOOOOOOOOOOOOo");
		Logger logger = CDI.current().select(Logger.class, new LoggerNameImp(LoggerNameValue.SYSTEM)).get();
		String result = obj;
		if ((StringUtils.isNoneEmpty(obj)) & (Level.FINE.intValue() >= logger.getLevel().intValue())) {
			result = String.format("%" + String.valueOf(obj.length()) + "s", "").replace(" ", "*");
		}
		return result;
	}

	@Override
	public String adaptFromJson(String obj) throws Exception {
		return obj;
	}

	@SuppressWarnings("all")
	class LoggerNameImp extends AnnotationLiteral<LoggerName> implements LoggerName {

		private static final long serialVersionUID = -7009086839775291735L;
		private LoggerNameValue value;
		
		public LoggerNameImp(LoggerNameValue value) {
			this.value = value;
		}
		
		@Override
		public LoggerNameValue value() {
			return value;
		}
		
	}
	
}
