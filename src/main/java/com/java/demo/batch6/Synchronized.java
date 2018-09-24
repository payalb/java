package com.java.demo.batch6;

public class Synchronized {

	public static void main(String[] args) {
		Task task1= new Task();
		Task task2= new Task();
		
		// new Thread(()-> task1.m1()).start(); //1: 2, 3, 4, 5, 6, 8
		 //new Thread(()-> task1.m3()).start(); //2
		 //new Thread(()-> task2.m1()).start();//3: 1,2,4,5,6,7,8
		 new Thread(()-> task2.m3()).start();//4
		// new Thread(()-> Task.m2()).start();//5: 1,2,3,4,6,7,8
		 new Thread(()-> Task.m4()).start();//6
		// new Thread(()-> task1.m1()).start(); //7: 2,3,4,5,6,8
		 //new Thread(()-> task1.m3()).start(); //8
		 
		 /*If a thread acquires an object lock : synchronized method, any other thread 
		  * can acquire a class lock (static synchronized method)or a non-synchronized method.
		  * No other thread can acquire the same object lock but can acquire a diff object lock
		  * 
		  * If a thread calling  a non-synchronized method, any 1 thread can acquire an object
		  *  and a class lock. There can be more than 1 thread calling a non-synchronized
		  *  method at a time (>3)
		  * 
		  * If a thread acquires a class lock, no other thread can acquire a class lock but
		  * other thread can acquire a object lock and many threads can execute a 
		  * non-synchronized method in parallel.
		  * 
		  * */
	}

}
//junit

class Task {

	public synchronized void m1() {
		System.out.println("m1 begin");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m1 end");
	}

	public static synchronized void m2() {
		System.out.println("m2 begin");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m2 end");
	}

	public void m3() {
		System.out.println("m3 begin");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m3 end");
	}

	public static void m4() {
		System.out.println("m4 begin");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m4 end");
	}
}