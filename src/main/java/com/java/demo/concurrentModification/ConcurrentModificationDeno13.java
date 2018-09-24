package com.java.demo.concurrentModification;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentModificationDeno13 {
	 /*using java 8 stream api*/
	
	/*Java 8 introduced the removeIf() method to the Collection interface.*/
	public static void main(String[] args) {
		List<Integer> integers = new ArrayList<>(List.of(1, 2, 3));
		 
		integers.removeIf(i -> i == 2);
		 
		System.out.println(integers);
	}
}
