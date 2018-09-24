package com.java.demo.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*Class is thread-safe, but the need to use a lock irks some developers
 *  because of the performance cost involved.*/
public class SynchronizedInsteadOfAtomic {

	public static void main(String[] args) throws InterruptedException {
		SynchronizedInsteadOfAtomic obj = new SynchronizedInsteadOfAtomic();
		ExecutorService ex= Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			ex.execute(()-> obj.increment());
			ex.execute(()-> obj.decrement());
			
		}
		ex.awaitTermination(5000, TimeUnit.MILLISECONDS);
		System.out.println(obj.value());
		ex.shutdown();
	}

	private int x = 0;

	public synchronized void increment() {
		++this.x;
	}

	public synchronized void decrement() {
		--this.x;
	}

	public synchronized int value() {
		return x;
	}
}
