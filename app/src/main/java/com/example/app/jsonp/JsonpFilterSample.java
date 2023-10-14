package com.example.app.jsonp;

import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonPointer;
import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class JsonpFilterSample {
	
	final private static String targetList = "{\"field\":\"\",\"object\":{\"field\":\"\",\"object\":{\"field\":\"\"}}}";

	final private static String removeList = "{\"field\":\"\",\"object\":{\"field\":\"\",\"object\":{\"field\": null}}}";

	public static void main(String[] args) {
		// selectedResponse();
		// selectedRemove();
		remove();
	}

	private static void remove() {
		JsonStructure original = generateSample();
		System.out.println(original);
		JsonPointer pointer = Json.createPointer("/object/fuga");
		// JsonValue value = pointer.getValue(original);
		JsonValue va =  JsonValue.EMPTY_JSON_OBJECT;
		JsonbConfig config = new JsonbConfig();
		config.withNullValues(true);
		Jsonb jsonb = JsonbBuilder.create(config);
		SampleResponse res = new SampleResponse();
		res.setField("Hoge");
		res.setObject(new SampleResponse());
		JsonStructure val = Json.createReader(new StringReader(jsonb.toJson(res))).read();
		System.out.println(val);
		JsonStructure after = pointer.add(val, va);
		after = pointer.add(val, va);
		System.out.println(after);
		after = pointer.add(val, va);
		System.out.println(after);
		after = pointer.add(val, va);
		System.out.println(after);
		after = pointer.add(val, va);
		System.out.println(after);
		after = pointer.add(val, va);
		System.out.println(after);
		pointer.remove(original);
		System.out.println(after);
		
	}
	
	
	/**
	 * JSONポインターもしくは、JSON構造体でNullと指定された値をレスポンス情報から除外する。
	 */
	private static void selectedRemove() {
		JsonStructure original = generateSample();
		
		JsonParser parser = Json.createParser(new StringReader(removeList));
		Deque<String> jsonPointer = new ArrayDeque<String>();
		jsonPointer.push("default");
		String propertyName = "default";
		while(parser.hasNext()) {
			Event event = parser.next();
			switch (event) {
			case END_ARRAY:
				System.out.println(event);
				break;
			case END_OBJECT:
				System.out.println(event);
				jsonPointer.pop();
				break;
			case KEY_NAME:
				System.out.println(event);
				jsonPointer.push(parser.getString());
				break;
			case START_ARRAY:
				System.out.println(event);
				break;
			case START_OBJECT:
				System.out.println(event);
				break;
			case VALUE_FALSE:
				System.out.println(event);
				break;
			case VALUE_NULL:
				System.out.println(event);
				Iterator<String>ite = jsonPointer.descendingIterator();
				StringBuilder strBuilder = new StringBuilder();
				ite.next();
				while(ite.hasNext()) {
					strBuilder.append("/");
					strBuilder.append(ite.next());
				}
				System.out.println(strBuilder.toString());
				JsonPointer pointer = Json.createPointer(strBuilder.toString());
				pointer.getValue(original);
				JsonStructure removedJson = pointer.remove(original);
				System.out.println(removedJson);

				break;
			case VALUE_NUMBER:
				System.out.println(event);
				break;
			case VALUE_STRING:
				System.out.println(event);
				jsonPointer.pop();
				break;
			case VALUE_TRUE:
				System.out.println(event);
				break;
			default:
				System.out.println(event);
				break;
			}
		}
		System.out.println(original);
		JsonPointer pointer = Json.createPointer("/object");
		pointer.getValue(original);
		JsonStructure removedJson = pointer.remove(original);
		System.out.println(removedJson);
		
	}
	
	private static void selectedResponse() {
		JsonStructure original = generateSample();
		
		JsonParser parser = Json.createParser(new StringReader(targetList));
		Deque<JsonObjectBuilder> deque = new ArrayDeque<JsonObjectBuilder>();
		Deque<String> objectName = new ArrayDeque<String>();
		// objectName.push("default");
		JsonObjectBuilder currentObjectBulder = Json.createObjectBuilder();
		String propertyName = "default";
		while (parser.hasNext()) {
			Event event = parser.next();
			switch (event) {
			case END_ARRAY:
				System.out.println(event);
				break;
			case KEY_NAME:
				System.out.println(Event.KEY_NAME.name());
				System.out.println(parser.getString());
				propertyName = parser.getString();
				break;
			case START_ARRAY:
				System.out.println(event);
				break;
			case START_OBJECT:
				// オブジェクトのプロパティ名を保存
				objectName.push(propertyName);
				System.out.println(event);
				// 新しいJSONオブジェクトの解析が始まったため、今まで処理していたJSONオブジェクトをpush
				deque.push(currentObjectBulder);
				// 新しいJSONオブジェクトに現在の変数を差し替え
				currentObjectBulder = Json.createObjectBuilder();
				// currentObjectBulder.add("hoge", "");
				break;
			case END_OBJECT:
				System.out.println(event);
				// 作成が完了したJSONオブジェクトを登録する。
				JsonObjectBuilder old = currentObjectBulder;
				currentObjectBulder = deque.pop();
				currentObjectBulder.add(objectName.pop(), old);
				break;
			case VALUE_FALSE:
				System.out.println(event);
				break;
			case VALUE_NULL:
				System.out.println(event);
				break;
			case VALUE_NUMBER:
				System.out.println(event);
				break;
			case VALUE_STRING:
				System.out.println(event);
				Iterator<String>ite = objectName.descendingIterator();
				StringBuilder strBuilder = new StringBuilder();
				ite.next();
				while(ite.hasNext()) {
					strBuilder.append("/");
					strBuilder.append(ite.next());
				}
				strBuilder.append("/");
				strBuilder.append(propertyName);
				
				System.out.println(strBuilder.toString());
				
				strBuilder.toString();
				currentObjectBulder.add(propertyName, original.getValue(strBuilder.toString()));
				break;
			case VALUE_TRUE:
				System.out.println(event);
				break;
			default:
				System.out.println(event);
				break;
			}
		}
		System.out.println(currentObjectBulder.build().getJsonObject("default").toString());
		
	}
	
	private static JsonStructure generateSample() {
		// ダミーのレスポンス情報を生成
		SampleResponse two = new SampleResponse();
		two.setField("last");
		SampleResponse one = new SampleResponse();
		one.setField("two");
		one.setObject(two);
		SampleResponse res = new SampleResponse();
		res.setField("one");
		res.setObject(one);

		// オブジェクト→JSONドキュメントへの変換
		JsonbConfig config = new JsonbConfig();
		config.withNullValues(true);
		Jsonb jsonb = JsonbBuilder.create(config);
		String originalJson = jsonb.toJson(res);
		// JSONドキュメントからJSONオブジェクトモデルの生成
		JsonStructure original = Json.createReader(new StringReader(originalJson)).read();

		return original;
	}
}
