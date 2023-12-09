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
		JsonStructure after = pointer.add(val, va);
		after = pointer.add(val, va);
		after = pointer.add(val, va);
		after = pointer.add(val, va);
		after = pointer.add(val, va);
		after = pointer.add(val, va);
		pointer.remove(original);
		
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
				break;
			case END_OBJECT:
				jsonPointer.pop();
				break;
			case KEY_NAME:
				jsonPointer.push(parser.getString());
				break;
			case START_ARRAY:
				break;
			case START_OBJECT:
				break;
			case VALUE_FALSE:
				break;
			case VALUE_NULL:
				Iterator<String>ite = jsonPointer.descendingIterator();
				StringBuilder strBuilder = new StringBuilder();
				ite.next();
				while(ite.hasNext()) {
					strBuilder.append("/");
					strBuilder.append(ite.next());
				}
				JsonPointer pointer = Json.createPointer(strBuilder.toString());
				pointer.getValue(original);
				JsonStructure removedJson = pointer.remove(original);

				break;
			case VALUE_NUMBER:
				break;
			case VALUE_STRING:
				jsonPointer.pop();
				break;
			case VALUE_TRUE:
				break;
			default:
				break;
			}
		}
		JsonPointer pointer = Json.createPointer("/object");
		pointer.getValue(original);
		JsonStructure removedJson = pointer.remove(original);
		
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
				break;
			case KEY_NAME:
				propertyName = parser.getString();
				break;
			case START_ARRAY:
				break;
			case START_OBJECT:
				// オブジェクトのプロパティ名を保存
				objectName.push(propertyName);
				// 新しいJSONオブジェクトの解析が始まったため、今まで処理していたJSONオブジェクトをpush
				deque.push(currentObjectBulder);
				// 新しいJSONオブジェクトに現在の変数を差し替え
				currentObjectBulder = Json.createObjectBuilder();
				// currentObjectBulder.add("hoge", "");
				break;
			case END_OBJECT:
				// 作成が完了したJSONオブジェクトを登録する。
				JsonObjectBuilder old = currentObjectBulder;
				currentObjectBulder = deque.pop();
				currentObjectBulder.add(objectName.pop(), old);
				break;
			case VALUE_FALSE:
				break;
			case VALUE_NULL:
				break;
			case VALUE_NUMBER:
				break;
			case VALUE_STRING:
				Iterator<String>ite = objectName.descendingIterator();
				StringBuilder strBuilder = new StringBuilder();
				ite.next();
				while(ite.hasNext()) {
					strBuilder.append("/");
					strBuilder.append(ite.next());
				}
				strBuilder.append("/");
				strBuilder.append(propertyName);
				
				
				strBuilder.toString();
				currentObjectBulder.add(propertyName, original.getValue(strBuilder.toString()));
				break;
			case VALUE_TRUE:
				break;
			default:
				break;
			}
		}
		
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
