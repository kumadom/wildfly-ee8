package com.example.app.com.jaxrs.constants;

import jakarta.ws.rs.Priorities;

public class AppPriorities {
	private AppPriorities() {
	}
	
	public static class FILTER {
		private FILTER() {		
		}
		public static final int COMMON_HEADER_PROCESS = Priorities.HEADER_DECORATOR + 100;
		public static final int SWAGGER_HEADER_PROCESS = COMMON_HEADER_PROCESS + 100;
	}
	
	public static class PRE_READ {
		private PRE_READ() {
		}
		public static final int JSON_BINDING_ERROR_PROCESS = Priorities.ENTITY_CODER + 100;
		public static final int COMMON_BODY_PROCESS = JSON_BINDING_ERROR_PROCESS + 100;
	}
	
	public static class PRE_WRITE {
		private PRE_WRITE() {
		}
		public static final int DSP_COMPATIBLE_PROCESS = Priorities.ENTITY_CODER + 100;
	}
}
