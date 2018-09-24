package com.java.demo.batch6;

import java.util.ArrayList;
import java.util.List;

public class WaitAndNotifyDemo3 {

	public static void main(String[] args) throws InterruptedException {
		MyList2 obj = new MyList2();
		for (int i = 0; i < 25; i++) {
			new Thread(() -> obj.add()).start();
			new Thread(() -> obj.remove()).start();
			new Thread(() -> obj.getElements()).start();
		}

		Thread.sleep(5000);
		System.out.println("Size of list is" + obj.list.size());// should be zero
	}

}

class MyList2 {

	List<Integer> list = new ArrayList<>();
	Object lock= new Object();

	public void add() {
		int x = (int) (Math.random() * 100);
		System.out.println("adding element" + x);
		/*1) Wait and notify shud be in synchronized block/method*/
		synchronized (lock) {
			list.add(x);
			/* 2)To which class this wait and notify belong to?: Object: On which a thread should acquire/release a lock*/
			lock.notify();
			//3) The object on which u are acquiring the lock should be immutable
		}

	}

	public void remove() {
		System.out.println("list size before removing" + list.size() + ":" + Thread.currentThread().getName());
		synchronized (lock) {
			while (list.size() <= 0) {
				try {
					/*To which class this wait and notify belong to?: Object: On which a thread should acquire/release a lock*/
					lock.wait(); 
					System.out.println(Thread.currentThread().getName() + " is waiting");
				} catch (InterruptedException e) {
					System.out.println(e.getMessage() + ":" + Thread.currentThread().getName());
				} 
			}
			list.remove(0);
		}
	}

	public synchronized void getElements() {
		System.out.println(list);
	}
}