package com.example.app.soa;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SampleSoapHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		System.out.println("handleMessage");
		return false;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		System.out.println("handleFault");
		return false;
	}

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub
		System.out.println("close");
	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		System.out.println("getHeaders");
		return null;
	}

}
