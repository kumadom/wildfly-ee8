package com.example.app.com.core.validation;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

public class CustomValidateBundle extends ResourceBundleMessageInterpolator {

	public CustomValidateBundle() {
		super(new PlatformResourceBundleLocator("messages"));

	}	
}