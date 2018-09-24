package com.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadException_Future {
	
	
	public static void main(String args[]) {
	//	MyExecutor svc= new MyExecutor(1,1,5000, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(1));
		ExecutorService svc= Executors.newFixedThreadPool(1);
		Future<Void> future=svc.submit(()-> {
				throw new RuntimeException("Test exception handling");
		});
		try {
			future.get();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} catch (ExecutionException e) {
			System.out.println(e.getMessage());
		}
		svc.shutdown();
	}

	
}
/*class MyExecutor extends ThreadPoolExecutor{
	public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected
	void afterExecute(Runnable r, Throwable t) {
		if(t!= null) {
		System.out.println("In exception handler");
		//System.out.println(t.getMessage());
		}
		System.out.println("Gracefully handled exception");
	}
}
*/