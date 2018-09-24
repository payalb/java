package com.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class CompletableFutureDemo {

	@Test
	public void testOne() {
		CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {
			assertFalse(Thread.currentThread().isDaemon());
			return s.toUpperCase();
		});
		assertEquals("MESSAGE", cf.getNow(null));

	}

	@Test
	public void testTwo() {
		CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
			assertTrue(Thread.currentThread().isDaemon());
			return s.toUpperCase();
		});
		assertNull(cf.getNow(null));
		assertEquals("MESSAGE", cf.join());
	}

	static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
		int count = 1;

		@Override
		public Thread newThread(Runnable runnable) {
			return new Thread(runnable, "custom-executor-" + count++);
		}
	});

	@Test
	public void thenApplyAsyncWithExecutorTest() {
		CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
			assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
			assertFalse(Thread.currentThread().isDaemon());
			return s.toUpperCase();
		}, executor);
		assertNull(cf.getNow(null));
		assertEquals("MESSAGE", cf.join());
	}

	@Test
	public void thenAcceptTest() {
		StringBuilder result = new StringBuilder();
		CompletableFuture.completedFuture("thenAccept message").thenAccept(s -> result.append(s));
		assertTrue("Result was empty", result.length() > 0);
		assertEquals(result.toString(), "thenAccept message");
	}

	@Test
	public void thenAcceptAsyncTest() {
		StringBuilder result = new StringBuilder();
		CompletableFuture<Void> cf = CompletableFuture.completedFuture("thenAcceptAsync message")
				.thenAcceptAsync(s -> result.append(s));
		cf.join();
		assertTrue("Result was empty", result.length() > 0);
	}

	@Test
	public void completeExceptionallyTest() {
		CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
				CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
		CompletableFuture<String> exceptionHandler = cf.handle((s, th) -> {
			return (th != null) ? "message upon cancel" : "";
		});
		cf.completeExceptionally(new RuntimeException("completed exceptionally"));
		assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
		try {
			cf.join();
			fail("Should have thrown an exception");
		} catch (CompletionException ex) {
			assertEquals("completed exceptionally", ex.getCause().getMessage());
		}
		assertEquals("message upon cancel", exceptionHandler.join());
	}

}
