package com.example.app.sample;

public class SampleConst {

	private SampleConst() {
	}
	
	public static class PropertyKey{
		public static final String PROPERTY_KEY_PREFIX = "app.com";
		public static final String DELIMITER = ".";
		public static final String FQN_PLACEHOLDER = "{FQN}";
		public static final String HOGE = PROPERTY_KEY_PREFIX + DELIMITER + FQN_PLACEHOLDER + DELIMITER + "hoge";
		
		private PropertyKey() {
		}
	}
	
}
