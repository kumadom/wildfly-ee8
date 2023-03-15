package com.example.app.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class SomeThread {

	public void name() throws InterruptedException, ExecutionException {
//	    Supplier<String> supplyString = new Supplier<String>() {
//	    	@Override
//	    	public String get() {
//	    		try {
//	    			System.out.println("---sleep---");
//					Thread.sleep(10000);
//					System.out.println("---wake---");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//	    		return "supplyOne";
//	    	};
//		};
//		
//	    Supplier<Integer> supplyInt = new Supplier<Integer>() {
//	    	@Override
//	    	public Integer get() {
//	    		return 1;
//	    	};
//		};
//		
//		CompletableFuture<String>futureString = CompletableFuture.supplyAsync(supplyString);
//		CompletableFuture<Integer>futureInt = CompletableFuture.supplyAsync(supplyInt);
//		
//		CompletableFuture<?>allFuture = CompletableFuture.allOf(futureString, futureInt);
//	    
//		allFuture.whenComplete((ret, ex) -> {
//			if (ex == null) {
//				try {
//					System.out.println(futureString.get());
//					System.out.println(futureInt.get());
//				} catch (InterruptedException | ExecutionException e) {
//					e.printStackTrace();
//				}
//				
//			} else {
//		        // いずれかが失敗した場合(いずれか1つの例外のみ取得される)
//		        System.err.println("err=" + ex);
//			}
//		}).get();
//		System.out.println("END");
//	    // CompletableFuture<Void> future =CompletableFuture.supplyAsync(initValueSupplier).thenAcceptAsync(valueConsumer);
//
	}

	private int tp = 0;

	public void testPerformance() {
//		long startTime = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++) {
//			new Thread(new Runnable(){
//
//				@Override
//				public void run() {
//					// synchronized (this) {tp++;}
//					tp++;
//				}}).start();
//		}
//        long endTime = System.currentTimeMillis();
//        
//        System.out.println("開始時刻：" + startTime + " ms");
//        System.out.println("終了時刻：" + endTime + " ms");
//        System.out.println("処理時間：" + (endTime - startTime) + " ms");
//
	}

	public static boolean awaitFlag = false;
	private volatile int countDown = 0;

	/**
	 * {@link CountDownLatch}の検証メソッド
	 * 
	 * @throws InterruptedException
	 */
	public void testCountDownLatch() throws InterruptedException {
//		CountDownLatch latch = new CountDownLatch(10000);
//		
//		class TestThread extends Thread{
//			public void run() {
//				
//					countDown = countDown + 1;
//					// System.out.println(countDown);
//				latch.countDown();
//				try {
//					latch.await();
//					// System.out.println("end");
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		}
//
//        long startTime = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++) {
//			new TestThread().start();
//		}
//		latch.await();
//        long endTime = System.currentTimeMillis();
//        
//        System.out.println("開始時刻：" + startTime + " ms");
//        System.out.println("終了時刻：" + endTime + " ms");
//        System.out.println("処理時間：" + (endTime - startTime) + " ms");
//		
	}

	private static boolean stopRequested;

	public void test() throws InterruptedException {
//		Thread backgroundThread = new Thread(() -> {
//			int i = 0;
//			while (!stopRequested) {
//				System.out.println(i++);
//			}
//		});
//		backgroundThread.start();
//
//		TimeUnit.SECONDS.sleep(10);
//		stopRequested = true;
	}

	/**
	 * 
	 */
	public void testBlockingQueue() {

	}

}
