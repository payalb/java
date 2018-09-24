package com.java.demo.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class OptimisticLockDemo {

	public static void main(String[] args) {
		StampedLock lock = new StampedLock();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		/*
		 * An optimistic read lock is acquired by calling tryOptimisticRead() which
		 * always returns a stamp without blocking the current thread, no matter if the
		 * lock is actually available. If there's already a write lock active the
		 * returned stamp equals zero. You can always check if a stamp is valid by
		 * calling lock.validate(stamp).
		 */ executor.submit(() -> {
			long stamp = lock.tryOptimisticRead();
			try {
				System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
				Thread.sleep(1);
				System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
				Thread.sleep(1);
				System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
				/*
				 * The optimistic lock is valid right after acquiring the lock. In contrast to
				 * normal read locks an optimistic lock doesn't prevent other threads to obtain
				 * a write lock instantaneously. After sending the first thread to sleep for one
				 * second the second thread obtains a write lock without waiting for the
				 * optimistic read lock to be released. From this point the optimistic read lock
				 * is no longer valid. Even when the write lock is released the optimistic read
				 * locks stays invalid.
				 * 
				 * So when working with optimistic locks you have to validate the lock every
				 * time after accessing any shared mutable variable to make sure the read was
				 * still valid.
				 */
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock(stamp);
			}
		});

		executor.submit(() -> {
			long stamp = lock.writeLock();
			try {
				System.out.println("Write Lock acquired");
				Thread.sleep(1);
			} catch (InterruptedException e) {
			} finally {
				lock.unlock(stamp);
				System.out.println("Write done");
			}
		});
	}

}
