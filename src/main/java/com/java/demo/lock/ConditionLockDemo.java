package com.java.demo.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConditionLockDemo {

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
	ReadWriteLock lock = new ReentrantReadWriteLock();
//	private Condition full = lock.writeLock().newCondition();
	private Condition empty = lock.writeLock().newCondition();

	public void add() {
		int x = (int) (Math.random() * 100);
		System.out.println("adding element" + x);
		lock.writeLock().lock();
		list.add(x);
		empty.signal();
		lock.writeLock().unlock();

	}

	public void remove()  {
		System.out.println("list size before removing" + list.size() + ":" + Thread.currentThread().getName());
		lock.writeLock().lock();
		while (list.size() <= 0) {
		try {
			//Will wait till another signals it to wake up
			empty.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		list.remove(0);
		lock.writeLock().unlock();
		
	}

	public  void getElements() {
		lock.readLock().lock();
		System.out.println(list);
		lock.readLock().unlock();
	}
}