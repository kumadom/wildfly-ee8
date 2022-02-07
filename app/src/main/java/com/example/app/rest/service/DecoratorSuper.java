package com.example.app.rest.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DecoratorSuper implements DecoratorIF {

	@Inject
	Samplehogehge hoge;
	
	@Override
	public String hoge() {
		hoge.name();
		hoge2();
		System.out.println("Super hoge");
		return null;
	}

	@Override
	public String hoge2() {
		System.out.println("Super hoge2");
		return null;
	}

}
