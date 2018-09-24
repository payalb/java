package com.java.demo.concurrentModification;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*Concurrent modification exception can come in case of multithreaded as well as 
 * single threaded java programming environment.*/
public class ConcurrentModificationDemo3 {

	public static void main(String[] args) {
		Map<String, String> myMap = new HashMap<String, String>();
		myMap.put("1", "1");
		myMap.put("2", "2");
		myMap.put("3", "3");

		//Map does not have iterator method. You need to get it from keyset or entryset
		Iterator<String> it = myMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println("Map Value:" + myMap.get(key));
			if (key.equals("2")) {
				//myMap.put("1", "4"); //This works
				 myMap.put("4", "4");
			}
		}
	}
	/*how Iterator checks for the modification, its implementation is present in
	 *  AbstractList class where an int variable modCount is defined.
	 *   modCount provides the number of times list size has been changed. 
	 *   modCount value is used in every next() call to check for any modifications 
	 *   in a function checkForComodification().*/
}
