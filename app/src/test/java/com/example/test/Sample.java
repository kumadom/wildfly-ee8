package com.example.test;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class Sample {

	@Test
	public void test() throws Exception {

		String unicode = new String("あ");

		byte[] sbytes = "あ".getBytes(StandardCharsets.UTF_8);
		byte[] bytes = new byte[2];
		bytes[0] = 66;
		bytes[1] = 48;
		System.out.println(new String(sbytes, StandardCharsets.UTF_8));
		System.out.println(new String(sbytes));
		System.out.println(getHexString(bytes[0]));
		System.out.println(getHexString(bytes[1]));
		System.out.println(new String(bytes));
		System.out.println(sbytes);;
		
		String a = new String(bytes);
		System.out.println(a);
		System.out.println(sbytes.length);
		System.out.println(sbytes);
		System.out.println(Hex.encodeHex(sbytes));

		
		
		sbytes = "い".getBytes(StandardCharsets.UTF_8);
		System.out.println(sbytes.length);
		System.out.println(sbytes);
		System.out.println(Hex.encodeHex(sbytes));

	}

	private String getHexString(byte b) {
		StringBuilder builder = new StringBuilder();
		return builder.append("0x").append(Integer.toHexString(Integer.valueOf(b))).toString();
	}
}
