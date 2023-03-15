package com.example.app;

import org.junit.Rule;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

public class WireMockTest {

	@Rule
	public WireMockClassRule wiremockClassRule = new WireMockClassRule(9090);

	public void test() {
//		SimpleAddBean service = new SimpleAddBean();
//		SimpleAddBeanPortType port = service.getSimpleAddBeanPort();
//		String reply = port.getSample();
//		System.out.println(reply);
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean(); 
//		factory.setServiceClass(SimpleAddBeanPortType.class);
//		factory.setHandlers(Arrays.asList(new SampleSoapHandler()));
//		factory.setAddress("http://localhost:9090/SimpleAddBeanPort"); 
//		SimpleAddBeanPortType client = (SimpleAddBeanPortType) factory.create();
//		String reply = client.getSample(); 

	}

}
