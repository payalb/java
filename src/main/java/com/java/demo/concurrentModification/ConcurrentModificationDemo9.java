package com.java.demo.concurrentModification;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentModificationDemo9 {
	/*Use simple for loop to avoid java.util.ConcurrentModificationException*/
	public static void main(String[] args) {
		List<String> myList = new ArrayList<>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");

		for(int i=0; i<myList.size(); i++) {
			String value = myList.get(i);
			System.out.println("List Value:" + value);
			if (value.equals("3")) {
				myList.remove("4");
				myList.add("6");
				myList.add("7");
			}
		}
		System.out.println("List " + myList);
	}

}
