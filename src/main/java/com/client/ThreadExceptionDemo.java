package com.client;

public class ThreadExceptionDemo {
	
	public static void main(String args[]) {
		try {
		Thread t= new Thread(()-> {
				throw new RuntimeException("Test exception handling");
		});
		t.start();
		}catch(Exception e) {
			//It will not go inside the catch block
			System.out.println("In catch!");
			System.out.println(e.getMessage());
		}
	}

}
