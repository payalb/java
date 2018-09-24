package com.java.demo.concurrentModification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModificationDemo5 {

	public static void main(String[] args) {
		//Use iterator to remove element
		List<String> list= new ArrayList<>(Arrays.asList("abc","def","ghi", "khg"));
		
		Iterator<String> it= list.iterator();
		while(it.hasNext()) {
			String temp= it.next();
			if(temp.contains("a")) {
				//When we call it.remove, it removes element being pointed currently
				it.remove();
				//No add method
			}
		}
		System.out.println(list);
	}

}
