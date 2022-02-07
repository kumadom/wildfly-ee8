package com.example.app.rest.service;

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.interceptor.Interceptor;

@Decorator
@Priority(Interceptor.Priority.APPLICATION)
//@ApplicationScoped
//@Alternative
//@Priority(value = 10)
public class DecoratorSub implements DecoratorIF {

//	@Inject
//	@Delegate
	private DecoratorIF superIf;
	
	@Inject
	public DecoratorSub(@Delegate DecoratorIF superIf ) {
		this.superIf = superIf;
	}
	
	@Override
	public String hoge() {
		superIf.hoge();
		System.out.println("Sub hoge");
		return null;
	}

	@Override
	public String hoge2() {
		superIf.hoge2();
		System.out.println("Sub hoge2");
		return null;
	}

}
