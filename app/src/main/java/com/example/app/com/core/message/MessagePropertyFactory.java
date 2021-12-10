package com.example.app.com.core.message;

import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MessagePropertyFactory {

	private final ResourceBundle messages;
	
	public MessagePropertyFactory() {
		messages = ResourceBundle.getBundle("messages");
	}
	
	@Produces
	@MessageProperty
	public ResourceBundle getMessageProperty() {
		return messages;
	}
}
