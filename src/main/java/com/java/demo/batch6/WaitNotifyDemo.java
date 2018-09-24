package com.java.demo.batch6;

import java.util.ArrayList;
import java.util.List;

//Too slow !!
public class WaitNotifyDemo {

	public static void main(String[] args) throws InterruptedException {
		// long startTime= System.currentTimeMillis();
		MyList obj = new MyList();
		for (int i = 0; i < 25; i++) {
			new Thread(() -> obj.add()).start();
			new Thread(() -> obj.remove()).start();
			new Thread(() -> obj.getElements()).start();
		}

		Thread.sleep(5000);
		System.out.println("Size of list is" + obj.list.size());// should be zero
	}
}

class MyList {

	List<Integer> list = new ArrayList<>();

//2) If no of elements in list is 0, 1 thread is adding an element at index 0, 
	// other is also trying to remove from it??
	// 3) Size of the list :10. Now it is trying to add a new element: it has to
	// resize and another thread is trying to remove at the same time
	// 4) List is not thread safe, more than 2 threads can try to insert/remove an
	// element at the same index
	public  void add() {
		int x = (int) (Math.random() * 100);
		System.out.println("adding element" + x);
		synchronized(this) {
		list.add(x);
		notify();
		}
		
	}

	// 1) If the list is empty, and remove is called: ArrayIndexOutOfBoundsException
	public  void remove() {// thread is acquiring a lock on the current object lock(obj/ this)
		System.out.println("list size before removing"+ list.size()+ ":"+ Thread.currentThread().getName());
		synchronized(this) {
		while (list.size() <= 0) {
			try {
				this.wait(); // this current thread will release the lock on this object: waiting 2
				System.out.println(Thread.currentThread().getName() +" is waiting");
			} catch (InterruptedException e) {
				System.out.println(e.getMessage()+ ":"+Thread.currentThread().getName());
			} // this thread should give up the lock?? object lock
		}
		list.remove(0);
		}
	}
//Thread t1 : size of list is 0 : waiting state .
	//t2 added an element. called notify
	//t3 acquired a lock: remove
	//t2 woke up, acquired lock  and did remove
	
	public synchronized void getElements() {
		System.out.println(list);
	}
}