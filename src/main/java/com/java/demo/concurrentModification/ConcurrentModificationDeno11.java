package com.java.demo.concurrentModification;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentModificationDeno11 {
	/*If using for-each loop, we should Not be Removing During Iteration.*/
	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		//Creating separate list to modify the collection
		List<String> newNames = new ArrayList<>();
		names.add("Java");
		names.add("PHP");
		names.add("SQL");
		names.add("Angular 2");

		for(String name: names) {
			if("Sql".equalsIgnoreCase(name)) {
				newNames.add(name);
			}
		}
		
		names.removeAll(newNames);
		System.out.println(names);
	}

}
