package com.java.demo.batch6;

public class DeadlockDemo {
	Object lock1= new Object();
	Object lock2= new Object();
	

	public static void main(String[] args) {
		DeadlockDemo obj= new DeadlockDemo();
		//synchronized : thread is acquiring a lock : obj1 and this thread is waiting for lock on obj2
		//other thread is waiting for it to release a lock (obj1) and this thread has acquired a lock on obj2
		//deadlock
		
			new Thread(()-> obj.m1()).start();
			new Thread(()-> obj.m2()).start();
		
		
	}

	public void m1() {
		synchronized(lock1) {
			System.out.println("lock on lock1 acquired");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(lock2) {
				System.out.println("lock on lock2 acquired");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("lock on lock2 released");
			}
			System.out.println("lock on lock1 released");
		}
	}
	
	public void m2() {
		synchronized(lock2) {
			System.out.println("lock on lock2 acquired");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(lock1) {
				System.out.println("lock on lock1 acquired");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("lock on lock1 released");
			}
			System.out.println("lock on lock2 released");
		}
	}
}
