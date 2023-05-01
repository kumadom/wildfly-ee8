package com.example.app.concurrent;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class SomeThreadTest {

	@Test
	public void testName() throws InterruptedException, ExecutionException {
		SomeThread target = new SomeThread();
		
		target.name();
		
	}
	
	@Test
	public void testLatche() throws InterruptedException {
		SomeThread target = new SomeThread();
		
		target.testCountDownLatch();
	}
	
	@Test
	public void testTest() throws InterruptedException {
		SomeThread target = new SomeThread();
		
		target.test();
	}
	
	@Test
	public void testPerformance() {
		SomeThread target = new SomeThread();
		target.testPerformance();
	}
	
}
