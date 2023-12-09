package com.example.app.json.filter;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonPointer;
import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;

class JsonPointerFilter {
	private final String[] referenceTokens;
	private final String jsonPointer;

	JsonPointerFilter(String jsonPointer) {
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

	/**
	 * 指定されたJSONPointerに紐づく対象を取得したJSONオブジェクトを応答する
	 * 
	 * JSONPointerで指定された要素までのリファレンストークンも保持する。
	 * 
	 * @param json
	 * @return
	 */
	JsonObject doFilter(final JsonStructure json) {
		final JsonPointer pointer = Json.createPointer(jsonPointer);
		JsonObject filterdJson = JsonValue.EMPTY_JSON_OBJECT;
		if (pointer.containsValue(json)) {
			// JSON情報が取得できた場合、後続のJSONPatchでエラーとならないように対象のパスまでのJSON構造を組み立てる
			if (1 < referenceTokens.length) {
				filterdJson = createObject(1);
			}
			filterdJson = Json.createPatchBuilder().add(jsonPointer, pointer.getValue(json)).build()
					.apply(filterdJson);
		}
		return filterdJson;
	}

	private JsonObject createObject(int index) {
		String referenceToken = referenceTokens[index];
		JsonObject obj = ++index < referenceTokens.length ? createObject(index) : JsonStructure.EMPTY_JSON_OBJECT;
		return Json.createObjectBuilder().add(referenceToken, obj).build();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof JsonPointerFilter)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode() {
		return jsonPointer.hashCode();
	}
}