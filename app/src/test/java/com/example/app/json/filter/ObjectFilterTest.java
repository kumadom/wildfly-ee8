package com.example.app.json.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class ObjectFilterTest {

	private static final Jsonb jsonb = JsonbBuilder.create();
	
	/**
	 * filterListに""を設定した場合にフィルタリング対象のオブジェクトがすべて取得できること。
	 */
	@Test
	public void testJsonFilter_001() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one"); 
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of(""));
		JsonFilterData result = target.doFilter(parent);
		// 検証
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}

	/**
	 * filterListに参照トークンを一つ（/data）設定した場合に/data配下のみ取得できること。
	 */
	@Test
	public void testJsonFilter_002() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of("/data"));
		JsonFilterData result = target.doFilter(parent);
		// 検証
		parent.setValue(null);
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}

	/**
	 * filterListに参照トークンを二つ"/data/data"を設定した場合に/data/data配下のみ取得できること。
	 */
	@Test
	public void testJsonFilter_003() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of("/data/data"));
		JsonFilterData result = target.doFilter(parent);
		// 検証
		parent.setValue(null);
		parent.getData().setValue(null);
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}
	
	/**
	 * filterListに"/"の連続をフィルタリングパス（"//data"）の最初に設定した場合に{@code IllegalArgumentException}が発生すること。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testJsonFilter_004() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of("//data"));
		target.doFilter(parent);
	}

	/**
	 * filterListに"/"の連続をフィルタリングパス（"/data//data"）の中間に設定した場合に{@code IllegalArgumentException}が発生すること。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testJsonFilter_005() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of("/data//data"));
		target.doFilter(parent);
	}

	/**
	 * filterListに"/"の連続をフィルタリングパス（"/data//"）の最後に設定した場合に{@code IllegalArgumentException}が発生すること。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testJsonFilter_006() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of("/data//"));
		target.doFilter(parent);
	}
		
	/**
	 * filterListに"/"で終了するフィルタリングパス（"/data/"）を設定した場合に{@code IllegalArgumentException}が発生すること。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testJsonFilter_007() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of("/data/"));
		target.doFilter(parent);
	}
	
	/**
	 * filterListに"/"で開始しないフィルタリングパス（"data"）を設定した場合に{@code IllegalArgumentException}が発生すること。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testJsonFilter_008() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of("parent"));
		target.doFilter(parent);
	}
	
	/**
	 * filterListに存在しないオブジェクトを指定した場合でも正常終了すること。戻りデータは何も返されないこと。
	 */
	@Test
	public void testObjectFilter_001() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of("/hoge"));
		JsonFilterData result = (JsonFilterData)target.doFilter(parent);
		// 検証
		assertThat(jsonb.toJson(result)).isEqualTo("{}");
	}
	
	/**
	 * filterListに存在するオブジェクトを指定した場合に絞り込み後のデータを返すこと。
	 * <条件>１階層目で絞り込みを行う場合
	 */
	@Test
	public void testObjectFilter_002() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of("/data"));
		JsonFilterData result = target.doFilter(parent);
		// 検証
		parent.setValue(null);
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}

	/**
	 * filterListに存在するオブジェクトを指定した場合に絞り込み後のデータを返すこと。
	 * <条件>２階層目で絞り込みを行う場合
	 */
	@Test
	public void testObjectFilter_003() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of("/data/data"));
		JsonFilterData result = target.doFilter(parent);
		// 検証
		parent.setValue(null);
		parent.getData().setValue(null);
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}

	/**
	 * filterListに存在するオブジェクトを指定した場合に絞り込み後のデータを返すこと。
	 * <条件>複数の絞り込み条件を指定する。
	 */
	@Test
	public void testObjectFilter_004() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		ObjectFilter<JsonFilterData> target = new ObjectFilter<JsonFilterData>(List.of("/data/data", "/data/data"));
		JsonFilterData result = target.doFilter(parent);
		// 検証
		parent.setValue(null);
		parent.getData().setValue(null);
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}


}
