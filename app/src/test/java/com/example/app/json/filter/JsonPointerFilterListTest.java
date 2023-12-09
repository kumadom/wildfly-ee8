package com.example.app.json.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class JsonPointerFilterListTest {

	private static final Jsonb jsonb = JsonbBuilder.create();
	
	/**
	 * filterListに""を設定した場合にフィルタリング対象のオブジェクトがすべて取得できること。
	 */
	@Test
	public void testJsonFilter_001() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one"); 
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of(""));
		JsonFilterData result = target.filterObject(parent);
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
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/data"));
		JsonFilterData result = target.filterObject(parent);
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
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/data/data"));
		JsonFilterData result = target.filterObject(parent);
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
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("//data"));
		target.filterObject(parent);
	}

	/**
	 * filterListに"/"の連続をフィルタリングパス（"/data//data"）の中間に設定した場合に{@code IllegalArgumentException}が発生すること。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testJsonFilter_005() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/data//data"));
		target.filterObject(parent);
	}

	/**
	 * filterListに"/"の連続をフィルタリングパス（"/data//"）の最後に設定した場合に{@code IllegalArgumentException}が発生すること。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testJsonFilter_006() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/data//"));
		target.filterObject(parent);
	}
		
	/**
	 * filterListに"/"で終了するフィルタリングパス（"/data/"）を設定した場合に{@code IllegalArgumentException}が発生すること。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testJsonFilter_007() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/data/"));
		target.filterObject(parent);
	}
	
	/**
	 * filterListに"/"で開始しないフィルタリングパス（"data"）を設定した場合に{@code IllegalArgumentException}が発生すること。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testJsonFilter_008() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("parent"));
		target.filterObject(parent);
	}
	
	/**
	 * filterListにnullを指定した場合{@code IllegalArgumentException}が発生すること。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testJsonFilter_009() {
		// テスト実施
		new JsonPointerFilterList(null);
	}

	/**
	 * filterListに""を指定した場合正常終了すること。
	 */
	@Test
	public void testJsonFilter_0010() {
		// テスト実施
		new JsonPointerFilterList(List.of(""));
	}

	/**
	 * filterListに存在しないオブジェクトを指定した場合でも正常終了すること。戻りデータは何も返されないこと。
	 */
	@Test
	public void testFilterObject_001() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/hoge"));
		JsonFilterData result = (JsonFilterData)target.filterObject(parent);
		// 検証
		assertThat(jsonb.toJson(result)).isEqualTo("{}");
	}
	
	/**
	 * filterListに存在するオブジェクトを指定した場合に絞り込み後のデータを返すこと。
	 * <条件>１階層目で絞り込みを行う場合
	 */
	@Test
	public void testFilterObject_002() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/data"));
		JsonFilterData result = target.filterObject(parent);
		// 検証
		parent.setValue(null);
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}

	/**
	 * filterListに存在するオブジェクトを指定した場合に絞り込み後のデータを返すこと。
	 * <条件>２階層目で絞り込みを行う場合
	 */
	@Test
	public void testFilterObject_003() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/data/data"));
		JsonFilterData result = target.filterObject(parent);
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
	public void testFilterObject_004() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/data/data", "/data/data"));
		JsonFilterData result = target.filterObject(parent);
		// 検証
		parent.setValue(null);
		parent.getData().setValue(null);
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}

	/**
	 * filterListに存在するオブジェクトを指定した場合に絞り込み後のデータを返すこと。
	 * <条件>
	 *  複数の絞り込み条件を指定する。
	 *  1件目で取得したデータより２件目で取得したデータのほうが少ない場合に、マージされていることを確認する。
	 */
	@Test
	public void testFilterObject_005() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(new JsonFilterData(new JsonFilterData(), "two"), "one");
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/data", "/data/data"));
		JsonFilterData result = target.filterObject(parent);
		// 検証
		parent.setValue(null);
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}

	/**
	 * filterListに存在するオブジェクトを指定した場合に絞り込み後のデータを返すこと。
	 * <条件>
	 * int値を指定してきた場合
	 */
	@Test
	public void testFilterObject_006() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(null, null);
		parent.setI(11);
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/i"));
		JsonFilterData result = target.filterObject(parent);
		// 検証
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}

	/**
	 * 配列指定をしてきた場合に異常終了すること。
	 * <条件>
	 * 配列を指定してきた場合
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFilterObject_007() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(null, null);
		parent.setLi(List.of(new JsonFilterData(null, "hoge")));;
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/li/0"));
		JsonFilterData result = target.filterObject(parent);
		// 検証
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}

	/**
	 * 配列指定をしてきた場合に異常終了すること。
	 * <条件>
	 * 配列の中の要素を指定してきた場合
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFilterObject_008() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(null, "fuga");
		parent.setLi(List.of(new JsonFilterData(null, "hoge")));;
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/li/0/value"));
		JsonFilterData result = target.filterObject(parent);
		// 検証
		parent.setValue(null);
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}

	/**
	 * 配列指定をしてきた場合に異常終了すること。
	 * <条件>
	 * 配列の中の要素を複数指定してきた場合
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFilterObject_009() {
		// テストデータ準備
		JsonFilterData parent = new JsonFilterData(null, "fuga");
		parent.setLi(List.of(new JsonFilterData(null, "hoge").setI(10)));
		// テスト実施
		JsonPointerFilterList target = new JsonPointerFilterList(List.of("/li/0/value", "/li/0/i"));
		JsonFilterData result = target.filterObject(parent);
		// 検証
		parent.setValue(null);
		assertThat(jsonb.toJson(result)).isEqualTo(jsonb.toJson(parent));
	}
}
