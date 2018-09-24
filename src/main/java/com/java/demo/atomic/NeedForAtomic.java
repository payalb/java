package com.java.demo.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NeedForAtomic {

	public static void main(String[] args) throws InterruptedException {
		Counter obj = new Counter();
		ExecutorService ex= Executors.newFixedThreadPool(10);
		for (int i = 0; i <= 10; i++) {
			ex.execute(()-> obj.increment());
			ex.execute(()-> obj.decrement());
		}
		ex.awaitTermination(2000, TimeUnit.MILLISECONDS);
		System.out.println(obj.value());
		//Will get diff values as increment and decrement are not atomic operations
		ex.shutdown();
	}
}

class Counter {
	private int x = 0;
	/*Three steps have to be performed in order to increment the number: 
	 * (i) read the current value, 
	 * (ii) increase this value by one and 
	 * (iii) write the new value to the variable. */
	public void increment() {
		System.out.println(x);
		++this.x;
	}

	public void decrement() {
		System.out.println(x);
		--this.x;
	}

	public int value() {
		return x;
	}
}