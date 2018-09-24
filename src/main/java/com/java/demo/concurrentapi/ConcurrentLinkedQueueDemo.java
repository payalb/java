package com.java.demo.concurrentapi;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {
	/*
	 * An unbounded thread-safe queue based on linked nodes.Like most other
	 * concurrent collection implementations, this class does not permit the use of
	 * null elements.Iterator does not throwConcurrentModificationException,
	 *  and may proceed concurrently with other operations. 
	 */
	public static void main(String[] args) {
		new Thread(()-> Task.add()).start();;
		new Thread(()-> Task.remove()).start();
		
	}
}

class Task {
	static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

	public static void add() {
		for (int i = 0; i < 10; i++) {
			queue.add((int) (Math.random() * 1000));
			System.out.println("Added element: "+ (i+1));
			
		}
	}

	public static void remove() {
		int i=0;
		while(i<10) {
			if(queue.peek()!=null) {
				System.out.println("removed"+ queue.poll());
				i++;
			}
			
		}
	}
}