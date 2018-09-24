package com.java.demo.concurrentModification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ConcurrentModificationDemo6 {

	public static void main(String[] args) {
		//Use iterator to remove element
		List<String> list= new ArrayList<>(Arrays.asList("abc","def","ghi", "khg"));
		//Using list iterator instead of plain iterator
		
		ListIterator<String> it= list.listIterator();
		while(it.hasNext()) {
			String temp= it.next();
			if(temp.contains("a")) {
				//When we call it.remove, it removes element being pointed currently
				it.remove();
				//Provides method to add an element too
				it.add("new"+ temp);
			}
		}
		System.out.println(list);

	}

}
