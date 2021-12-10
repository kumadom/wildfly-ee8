package com.example.app.com.core.message;

import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MessagePropertyFactory {

	@Produces
	@MessageProperty
	public ResourceBundle getMessageProperty() {
		return ResourceBundle.getBundle("errorMessages");
	}
}
