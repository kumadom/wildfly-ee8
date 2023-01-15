package com.example.app.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.persistence.criteria.CriteriaBuilder.In;

public class SomeThread {

	public void name() throws InterruptedException, ExecutionException {
	    Supplier<Integer> initValueSupplier = () -> 100;
	    Consumer<Integer> valueConsumer = value -> System.out.println(value);

	    Supplier<String> supplyString = new Supplier<String>() {
	    	@Override
	    	public String get() {
	    		System.out.println(Thread.currentThread().getId());
	    		System.out.println(Thread.currentThread().getName());
	    		try {
	    			System.out.println("---sleep---");
					Thread.sleep(10000);
					System.out.println("---wake---");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		return "supplyOne";
	    	};
		};
		
	    Supplier<Integer> supplyInt = new Supplier<Integer>() {
	    	@Override
	    	public Integer get() {
	    		return 1;
	    	};
		};
		
		CompletableFuture<String>futureString = CompletableFuture.supplyAsync(supplyString);
		CompletableFuture<Integer>futureInt = CompletableFuture.supplyAsync(supplyInt);
		
		CompletableFuture<?>allFuture = CompletableFuture.allOf(futureString, futureInt);
	    
		allFuture.whenComplete((ret, ex) -> {
			if (ex == null) {
				try {
					System.out.println(futureString.get());
					System.out.println(futureInt.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
		        // いずれかが失敗した場合(いずれか1つの例外のみ取得される)
		        System.err.println("err=" + ex);
			}
		}).get();
		System.out.println("END");
	    // CompletableFuture<Void> future =CompletableFuture.supplyAsync(initValueSupplier).thenAcceptAsync(valueConsumer);

	}
	
	
}
