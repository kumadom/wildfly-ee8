package com.example.app.com.core.message;

import java.util.ResourceBundle;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MessagePropertyFactory {

	private final ResourceBundle messages;
	
	public MessagePropertyFactory() {
		messages = ResourceBundle.getBundle("messagesCode");
	}
	
	@Produces
	@MessageProperty
	public ResourceBundle getMessageProperty() {
		return messages;
	}
}
