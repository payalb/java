package com.java.demo.concurrentModification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModificationDemo2 {

	//All iterators are fail-fast.
	//If using iterator to iterate over a collection, must not modify below collection
	public static void main(String[] args) {
		
		List<String> list= new ArrayList<>(Arrays.asList("abc","def","ghi", "khg"));
		Iterator<String> it=list.iterator();
		while(it.hasNext()) {
			String temp=it.next();
			if(temp.contains("a")) {
				list.remove(temp);
			}
		}
	}

}
