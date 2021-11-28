package com.example.app.com.swagger;

import io.swagger.v3.jaxrs2.Reader;
import io.swagger.v3.jaxrs2.ReaderListener;
import io.swagger.v3.oas.models.OpenAPI;

@io.swagger.annotations.SwaggerDefinition
public class SwaggerDefinition implements ReaderListener {

	@Override
	public void beforeScan(Reader reader, OpenAPI openAPI) {
	}

	@Override
	public void afterScan(Reader reader, OpenAPI openAPI) {
	}
	
}
