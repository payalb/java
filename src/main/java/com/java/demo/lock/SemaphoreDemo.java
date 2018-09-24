package com.java.demo.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SemaphoreDemo {

	static int count = 0;

	/*
	 * Whereas locks usually grant exclusive access to variables or resources, a
	 * semaphore is capable of maintaining whole sets of permits. This is useful in
	 * different scenarios where you have to limit the amount concurrent access to
	 * certain parts of your application.
	 */
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		/*
		 * The executor can potentially run 10 tasks concurrently but we use a semaphore
		 * of size 5, thus limiting concurrent access to 5. It's important to use a
		 * try/finally block to properly release the semaphore even in case of
		 * exceptions.
		 */ Semaphore semaphore = new Semaphore(5);
		Runnable longRunningTask = () -> {
			boolean permit = false;
			try {
				permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
				if (permit) {
					System.out.println("Semaphore acquired" + ++count);
					Thread.sleep(5);
				} else {
					System.out.println("Could not acquire semaphore");
				}
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			} finally {
				if (permit) {
					semaphore.release();
				}
			}
		};

		IntStream.range(0, 10).forEach(i -> executor.submit(longRunningTask));

		executor.awaitTermination(3, TimeUnit.SECONDS);

		executor.shutdown();

	}

}
