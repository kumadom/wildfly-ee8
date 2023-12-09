package com.example.app.json.filter;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonPointer;
import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class ObjectFilter<T> {

	// Jsonbはスレッドセーフ
	private final static Jsonb jsonb = JsonbBuilder.create();

	private final List<JsonPointerFilter> filterList;

	private class JsonPointerFilter {
		public final String[] referenceTokens;
		public final String jsonPointer;

		public JsonPointerFilter(String jsonPointer) {
			this.jsonPointer = jsonPointer;
			this.referenceTokens = jsonPointer.split("/", -1);
			if (!"".equals(referenceTokens[0])) {
				throw new IllegalArgumentException("フィルタリングパスの先頭は\"\"か\"/\"である必要があります。");
			}
			// jsonPointerが"/"の連続と、"/"で終わる場合はエラーとする
			for (int i = 1; i < referenceTokens.length; i++) {
				if ("".equals(referenceTokens[i])) {
					throw new IllegalArgumentException("フィルタリングパス内での\"/\"の連続、および終了は許容していません。");
				}
			}
		}
	}

	public ObjectFilter(List<String> filterList) {
		this.filterList = new ArrayList<JsonPointerFilter>();
		for (String jsonPointer : filterList) {
			JsonPointerFilter sample = new JsonPointerFilter(jsonPointer);
			this.filterList.add(sample);
		}
	}

	/**
	 * 対象のオブジェクトをフィルタリングする。
	 * 
	 * nullが指定された場合にはnullを応答する。 対象のオブジェクトにフィルタ条件に一致するデータがない場合、空のオブジェクトを応答する。
	 * 
	 * @param obj
	 * @return
	 */
	public T doFilter(T obj) {
		if (Objects.isNull(obj)) {
			return null;
		}
		// オブジェクトをJSON文字列に変換
		JsonStructure filteredJson = JsonStructure.EMPTY_JSON_OBJECT;
		JsonStructure originalJson = Json.createReader(new StringReader(jsonb.toJson(obj))).read();

		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		for (JsonPointerFilter sample : filterList) {
			JsonPointer pointer = Json.createPointer(sample.jsonPointer);
			if (pointer.containsValue(originalJson)) {
				// JSON文字列から欲しい対象を取得
				JsonValue filterdValue = pointer.getValue(originalJson);
				// JSON情報が取得できた場合、後続のJSONPatchでエラーとならないように対象のパスまでのJSON構造を組み立てる
				String path = "";
				for (int i = 1; i < sample.referenceTokens.length; i++) {
					path = path + "/" + sample.referenceTokens[i];
					JsonArray jsonArray = Json.createArrayBuilder().add(Json.createObjectBuilder().add("op", "add")
							.add("path", path).add("value", JsonStructure.EMPTY_JSON_OBJECT).build()).build();
					filteredJson = Json.createPatch(jsonArray).apply(filteredJson);
				}
				jsonArrayBuilder.add(Json.createObjectBuilder().add("op", "add").add("path", sample.jsonPointer)
						.add("value", filterdValue).build());
			}
		}
		filteredJson = Json.createPatch(jsonArrayBuilder.build()).apply(filteredJson);
		@SuppressWarnings("unchecked")
		T returnObject = (T) jsonb.fromJson(filteredJson.toString(), obj.getClass());
		return returnObject;
	}
}
