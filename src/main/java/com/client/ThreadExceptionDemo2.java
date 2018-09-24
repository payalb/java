package com.client;

public class ThreadExceptionDemo2 {
	
	static Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
	    public void uncaughtException(Thread th, Throwable ex) {
	        System.out.println("Uncaught exception: " + ex);
	    }
	};
	
	public static void main(String args[]) {
		Thread t= new Thread(()-> {
				throw new RuntimeException("Test exception handling");
		});
		t.start();
		t.setUncaughtExceptionHandler(h);
		
	}
	

}
