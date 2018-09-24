package com.java.demo.concurrentModification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcurrentModificationDemo1 {

	public static void main(String[] args) {

			List<String> list= new ArrayList<>(Arrays.asList("abc","def","ghi", "khg"));
			//If we modify a collection while iterating over it, leads to this exception
			for(String s: list) {
				if(s.contains("a")) {
					list.remove(s);
				}
			}
			
	}

}
