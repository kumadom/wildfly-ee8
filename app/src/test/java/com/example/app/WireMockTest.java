package com.example.app;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

public class WireMockTest {

	@Rule
	public WireMockClassRule wiremockClassRule = new WireMockClassRule(9090);
	
	@Test
	public void test() {
		System.out.println();
	}
	
}
