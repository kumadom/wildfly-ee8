package com.example.app.com.jaxrs.binding;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class AppJsonConfig implements ContextResolver<Jsonb> {
	
	private final Jsonb jsonb;

	public AppJsonConfig() {
		JsonbConfig config = new JsonbConfig().withNullValues(true);
		this.jsonb = JsonbBuilder.create(config);
	}
	
	@Override
	public Jsonb getContext(Class<?> type) {
		return jsonb;
	}
}
