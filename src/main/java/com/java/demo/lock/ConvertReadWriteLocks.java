package com.java.demo.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class ConvertReadWriteLocks {
	/*
	 * Sometimes it's useful to convert a read lock into a write lock without
	 * unlocking and locking again. StampedLock provides the method
	 * tryConvertToWriteLock() for that purpose
	 * 
	 */
	static int count=0;
	public static void main(String[] args) throws InterruptedException {
		 ExecutorService executor = Executors.newFixedThreadPool(2);
		 StampedLock lock = new StampedLock();
		 /*We first have to convert the read lock into a write lock to not break potential 
		  * concurrent access by other threads. Calling tryConvertToWriteLock() doesn't
		  *  block but may return a zero stamp indicating that no write lock is currently
		  *   available. In that case we call writeLock() to block the current thread 
		  *   until a write lock is available.
*/		 executor.submit(() -> {
		     long stamp = lock.readLock();
		     try {
		         if (count == 0) {
		             stamp = lock.tryConvertToWriteLock(stamp);
		             if (stamp == 0L) {
		                 System.out.println("Could not convert to write lock");
		                 stamp = lock.writeLock();
		             }
		             count = 23;
		         }
		         System.out.println(count);
		     } finally {
		         lock.unlock(stamp);
		     }
		 });

		 executor.awaitTermination(2000, TimeUnit.MILLISECONDS);
		 executor.shutdown();
	}

}
