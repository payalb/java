package com.java.demo.concurrentapi;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo2 {

	//JDK 5
	public static void main(String[] args) {
		ConcurrentLinkedQueue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<Integer>();
        concurrentLinkedQueue.add(100);
        concurrentLinkedQueue.add(200);
        concurrentLinkedQueue.add(300);
        concurrentLinkedQueue.add(400);
        concurrentLinkedQueue.add(500);
 
        System.out.println("the elements of the arrayblockingqueue is ");
        Iterator<Integer> itr = concurrentLinkedQueue.iterator();
        while (itr.hasNext())
        {
            System.out.print(itr.next() + "\t");
        }
        System.out.println();
        concurrentLinkedQueue.offer(600);
        concurrentLinkedQueue.offer(700);
        System.out.println("the peak element of the concurrentLinkedQueue is(by peeking) "
            + concurrentLinkedQueue.peek());
        System.out.println("the peak element of the concurrentLinkedQueue is(by polling) "
            + concurrentLinkedQueue.poll());
        System.out.println("element 300 removed " + concurrentLinkedQueue.remove(300));
        System.out.println("the concurrentLinkedQueue contains 400 :"
            + concurrentLinkedQueue.contains(400));
        System.out.println("the hash concurrentLinkedQueue contains 100 :"
            + concurrentLinkedQueue.contains(100));
        System.out.println("the size of the concurrentLinkedQueue is "
           + concurrentLinkedQueue.size());
        concurrentLinkedQueue.forEach(System.out::println);
	}

}
