package com.example.app.sample;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;

@ApplicationScoped
public class AbstractSampleResource {

	
	private ChildService sample;
	
	@Inject
	public AbstractSampleResource(ChildService sf) {
		System.out.println("------------------------------");
		System.out.println("AbstractSampleResource");
		System.out.println("------------------------------");
		sample = sf;
	}
	
	public void hoge() {
		Thread thread = new Thread() {
			public void run() {
				RequestContextController controller = CDI.current().select(RequestContextController.class).get();
				controller.activate();
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("hogegegegegege");
			}
		};
		thread.run();
		System.out.println("------------------------------");
		System.out.println("com.example.app.sample.AbstractSampleResource");
		
		System.out.println("------------------------------");
	}
	
}
