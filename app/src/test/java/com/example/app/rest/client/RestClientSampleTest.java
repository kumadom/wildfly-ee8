package com.example.app.rest.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.junit.Test;

public class RestClientSampleTest {

	@Test
	public void test() {
		RestClientSample sample = new RestClientSample();
		SystemDefaultDnsResolver resolver = SystemDefaultDnsResolver.INSTANCE;
		try {
			InetAddress[] addresses =  resolver.resolve("qiita.com");
			for(InetAddress address: addresses) {
				System.out.println(address.getCanonicalHostName());
				System.out.println(address.getHostAddress());
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
