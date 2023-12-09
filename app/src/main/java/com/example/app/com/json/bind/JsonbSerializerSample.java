package com.example.app.com.json.bind;

import jakarta.json.bind.serializer.JsonbSerializer;
import jakarta.json.bind.serializer.SerializationContext;
import jakarta.json.stream.JsonGenerator;

public class JsonbSerializerSample implements JsonbSerializer<String> {

	@Override
	public void serialize(String obj, JsonGenerator generator, SerializationContext ctx) {
		generator.writeStartObject().write("obj", obj).writeEnd();
		// ctx.serialize(generator);
		
	}

}
