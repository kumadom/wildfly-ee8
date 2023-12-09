package com.example.app.json.filter;

import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonStructure;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

/**
 * なお、JSON配列の指定は許容しない。
 * 
 * 工数的な問題かつ。配列配下の個別の置き換えなどはrfc7386の中でもオブジェクトではないターゲットの一部にパッチが適用することはできない。と記載がある。
 * 
 * かつ、配列をフィルタリングした結果、当初の順序性と異なる状態になるため、
 * それらの順序性の保証というのがJSON上では基本的には保証する方針とあるが、それらのルールに違反するため。
 */
public class JsonPointerFilterList {

	// Jsonbはスレッドセーフ
	private final static Jsonb jsonb = JsonbBuilder.create();

	private final Set<JsonPointerFilter> filterSet;

	public JsonPointerFilterList(List<String> filterList) {
		if (filterList == null) {
			throw new IllegalArgumentException("フィルタリングリストにnullは設定不可");
		}
		filterSet = new HashSet<JsonPointerFilter>();
		for (String jsonPointer : filterList) {
			JsonPointerFilter filter = new JsonPointerFilter(jsonPointer);
			filterSet.add(filter);
		}
	}

	/**
	 * 対象のオブジェクトをフィルタリングする。
	 * 
	 * nullが指定された場合にはnullを応答する。 対象のオブジェクトにフィルタ条件に一致するデータがない場合、空のオブジェクトを応答する。
	 * @param <T> JSONBによるシリアライズ、デシリアライズが正常に行えるオブジェクトの型
	 * @param obj
	 * @return
	 */
	public <T> T filterObject(T obj) {
		if (obj == null) {
			return null;
		}
		JsonStructure json = Json.createReader(new StringReader(jsonb.toJson(obj))).read();
		JsonObject filteredJson = filterJson(json);
		@SuppressWarnings("unchecked")
		T returnObject = (T) jsonb.fromJson(filteredJson.toString(), obj.getClass());
		return returnObject;
	}
	
	public JsonObject filterJson(JsonStructure json) {
		JsonObject filteredJson = JsonStructure.EMPTY_JSON_OBJECT;
		for (JsonPointerFilter filter : filterSet) {
			filteredJson = (JsonObject) Json.createMergePatch(filter.doFilter(json)).apply(filteredJson);
		}
		return filteredJson;
	}
}
