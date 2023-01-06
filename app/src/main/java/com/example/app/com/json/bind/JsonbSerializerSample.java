package com.example.app.com.json.bind;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class JsonbSerializerSample implements JsonbSerializer<String> {

	@Override
	public void serialize(String obj, JsonGenerator generator, SerializationContext ctx) {
		System.out.println("serializer");
		generator.writeStartObject().write("obj", obj).writeEnd();
		// ctx.serialize(generator);
		
	}

}
