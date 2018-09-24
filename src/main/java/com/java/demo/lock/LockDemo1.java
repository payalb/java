package com.java.demo.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockDemo1 {

	public static void main(String[] args) {
		Counter obj = new Counter();
		ExecutorService ex= Executors.newFixedThreadPool(10);
		for (int i = 0; i <= 10; i++) {
			ex.execute(()-> obj.increment());
			ex.execute(()-> obj.decrement());
		}
		try {
			ex.awaitTermination(2000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final object value"+ obj.value());
		ex.shutdown();
	}

}
class Counter {
	private int x = 0;
	Lock lock= new ReentrantLock();
	public void increment() {
		lock.lock();
		try {
		System.out.println(x);
		++this.x;
		}finally {
		lock.unlock();
		}
	}

	public void decrement() {
		lock.lock();
		try {
		System.out.println(x);
		--this.x;
		}finally {
		/* It's important to wrap your code into a try/finally block to ensure
		 *  unlocking in case of exceptions.
*/		lock.unlock();
		}
	}

	public int value() {
		return x;
	}
}