package com.example.app.com.json.bind.serializer;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class CustomJsonbSerializer implements JsonbSerializer<String> {

	@Override
	public void serialize(String obj, JsonGenerator generator, SerializationContext ctx) {
		System.out.println("oooooooooooooooooooooooooooooooooo");
		System.out.println(obj);
		System.out.println(ctx.toString());
		System.out.println(generator.toString());
		//ctx.serialize(obj, obj, generator);
		System.out.println("oooooooooooooooooooooooooooooooooo");
		System.out.println("oooooooooooooooooooooooooooooooooo");
		throw new RuntimeException();
	}

}
