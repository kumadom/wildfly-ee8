package com.example.test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Sample {

	@Test
	public void test() throws Exception {

		String unicode = new String("あ");

		byte[] sbytes = "あ".getBytes(StandardCharsets.UTF_8);
		byte[] bytes = new byte[2];
		bytes[0] = 66;
		bytes[1] = 48;
		
		String a = new String(bytes);
		sbytes = "い".getBytes(StandardCharsets.UTF_8);

	}

	@Test
	public void test1() {
		Map<String, Object> map = new HashMap<>();
		map.put("hoge", "h");
		map.put("fuga", new ArrayList<>());
		
		Hoge hoge = new Hoge();
		hoge.fuga = map.get("fuga") instanceof String fuga? (String)fuga: null;
		System.out.println(hoge.fuga);
	}
	
	private class Hoge {
		private String hoge;
		private String fuga;
	}
	
	private String getHexString(byte b) {
		StringBuilder builder = new StringBuilder();
		return builder.append("0x").append(Integer.toHexString(Integer.valueOf(b))).toString();
	}
}
