package com.example.app.sample;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SampleService extends AbstractSampleResource {

	
	private ChildService as;
	
	@Inject
    public SampleService(ChildService srv) {
		super(srv);
		System.out.println("------------------------------");
		System.out.println("SampleResource");
		System.out.println("------------------------------");
		as = srv;
	}
	
	public SampleService() {
		super(null);
		System.out.println("PROXY");
		System.out.println("------------------------------");
	}
	
	public void aa() {
		System.out.println("------------------------------");
		System.out.println(getClass());
		System.out.println(as.toString());
		System.out.println("------------------------------");
		this.hoge();
	}
}
