package com.example.app.soa;

import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

@ApplicationScoped
public class SoaSample {

	public void sample() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean(); 
		factory.setServiceClass(SimpleAddBeanPortType.class);
		factory.setHandlers(Arrays.asList(new SampleSoapHandler()));
		factory.setAddress("http://localhost:9090/SimpleAddBeanPort"); 
		SimpleAddBeanPortType client = (SimpleAddBeanPortType) factory.create();
		String reply = client.getSample(); 
		System.out.println("Server said: " + reply); 
	}
	
}
