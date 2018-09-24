package com.java.demo.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

	// Jdk1.8
	/*
	 * Java 8 ships with a new kind of lock called StampedLock which also support
	 * read and write locks just like in the example above. In contrast to
	 * ReadWriteLock the locking methods of a StampedLock return a stamp represented
	 * by a long value. You can use these stamps to either release a lock or to
	 * check if the lock is still valid. Additionally stamped locks support another
	 * lock mode called optimistic locking.
	 */
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Map<String, String> map = new HashMap<>();
		StampedLock lock = new StampedLock();

		executor.submit(() -> {
			long stamp = lock.writeLock();
			try {
				map.put("foo", "bar");
			} finally {
				lock.unlockWrite(stamp);
			}
		});

		Runnable readTask = () -> {
			long stamp = lock.readLock();
			try {
				System.out.println(map.get("foo"));
			} finally {
				lock.unlockRead(stamp);
			}
		};
		/*
		 * stamped locks don't implement reentrant characteristics. Each call to lock
		 * returns a new stamp and blocks if no lock is available even if the same
		 * thread already holds a lock. So you have to pay particular attention not to
		 * run into deadlocks.
		 */ executor.submit(readTask);
		executor.submit(readTask);
		/*both read tasks have to wait until the write lock has been released. 
		 * Then both read tasks print to the console simultaneously because multiple 
		 * reads doesn't block each other as long as no write-lock is held.
*/		executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
		executor.shutdown();
	}

}
