package com.example.app.com.jaxrs.constants;

import javax.ws.rs.Priorities;

public class AppPriorities {
	private AppPriorities() {
		
	}
	
	public static class FILTER{
		private FILTER() {
			
		}
		public static final int COMMON_HEADER_PROCESS = Priorities.HEADER_DECORATOR + 100;
	}
}
