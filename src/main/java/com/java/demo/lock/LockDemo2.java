package com.java.demo.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo2 {
	/*
	 * The method tryLock() as an alternative to lock() tries to acquire the lock
	 * without pausing the current thread. The boolean result must be used to check
	 * if the lock has actually been acquired before accessing any shared mutable
	 * variables.
	 */
	public static void main(String[] args) {
		Counter obj = new Counter();
		ExecutorService ex = Executors.newFixedThreadPool(10);
		for (int i = 0; i <= 10; i++) {
			ex.execute(() -> obj.increment());
			ex.execute(() -> obj.decrement());
		}
		try {
			ex.awaitTermination(2000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final object value" + obj.value());
		ex.shutdown();
	}

}

class Counter1 {
	private int x = 0;
	Lock lock = new ReentrantLock();

	// tryLock prevents deadlock
	public void increment() throws InterruptedException {
		boolean lockAcquired = lock.tryLock(2000, TimeUnit.MILLISECONDS);
		try {
			System.out.println(x);
			++this.x;
		} finally {
			if (lockAcquired) {
				lock.unlock();
			}
		}
	}

	public void decrement() throws InterruptedException {
		boolean lockAcquired = lock.tryLock(2000, TimeUnit.MILLISECONDS);
		try {
			System.out.println(x);
			--this.x;
		} finally {
			if (lockAcquired) {
				lock.unlock();
			}
		}
	}

	public int value() {
		return x;
	}
}
