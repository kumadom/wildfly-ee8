package com.example.app.com.json.bind;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class AppJsonConfig implements ContextResolver<Jsonb> {
	
	private final Jsonb jsonb;

	public AppJsonConfig() {
		JsonbConfig config = new JsonbConfig().withNullValues(false);
		this.jsonb = JsonbBuilder.create(config);
	}
	
	@Override
	public Jsonb getContext(Class<?> type) {
		return jsonb;
	}
}
