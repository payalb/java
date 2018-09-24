package com.java.demo.concurrentModification;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentModificationDeno10 {
	/*You will get ConcurrentModificationException if you will try to 
	 * modify the structure of original list with subList.*/
	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("Java");
		names.add("PHP");
		names.add("SQL");
		names.add("Angular 2");

		List<String> first2Names = names.subList(0, 2);

		System.out.println(names + " , " + first2Names);

		names.set(1, "JavaScript");
		// check the output below. :)
		System.out.println(names + " , " + first2Names);

		// Let's modify the list size and get ConcurrentModificationException
		names.add("NodeJS");
		System.out.println(names + " , " + first2Names); // this line throws exception
		/*According to the ArrayList subList documentation, structural modifications is
		 *  allowed only on the list returned by subList method.
		 *   All methods on returned list first check to see if the actual modCount
		 *    of the backing list is equal to its expected value, and throw a
		 *     ConcurrentModificationException if it is not.*/
	}

}
