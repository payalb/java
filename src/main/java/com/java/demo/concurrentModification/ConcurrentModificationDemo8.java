package com.java.demo.concurrentModification;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModificationDemo8 {
/*CopyOnWriteArrayList in Java is a thread safe implementation of
 *  List interface. This is a thread-safe variant of 
 *  java.util.ArrayList in which all mutative operations
 *   (add, set, and so on) are implemented by making a fresh 
 *   copy of the underlying array.*/
	public static void main(String[] args) {
		/*In case of CopyOnWriteArrayList, iterator iterates on the original list
		 * and modification is done on the .*/
		List<String> myList = new CopyOnWriteArrayList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");

		Iterator<String> it = myList.iterator();
		while (it.hasNext()) {
			String value = it.next();
			System.out.println("List Value:" + value);
			if (value.equals("3")) {
				myList.remove("4");
				myList.add("6");
				myList.add("7");
			}
		}
		System.out.println("List " + myList);
		/*Notice that it allows the modification of list, but it doesn’t change the iterator 
		 * and we get same elements as it was on original list.*/
	}

}
