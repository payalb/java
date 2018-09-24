package com.java.demo.concurrentapi;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapDemo {
	//JDK1.6
	/*Null value and null keys are not allowed. */
	public static void main(String[] args) {
		ConcurrentSkipListMap<String, String> ob= new ConcurrentSkipListMap<>();
		ob.put("3","A");
		ob.put("2","B");
		ob.put("1","C");
		ob.put("5","D");
		ob.put("4","E");
       //returns a key-value mapping of the least value but greater than the given key.
		System.out.println("ceiling entry of 3:"+ob.ceilingEntry("3"));
		
		// returns the NavigableSet in reverse order
		 NavigableSet<String> ns=ob.descendingKeySet();
		 
		System.out.println("Values in reverse order......");
		 Iterator<String> itr=ns.iterator();
		 while(itr.hasNext()){
			 String s = (String)itr.next();
			 System.out.println(s);
		 }
        
		 // returns the  key value pair of least key in the map
		 System.out.println("Value with least key: "+ob.firstEntry());
		 
		 // returns the  key value pair of greatest key in the map
		 System.out.println("Value with greatest key: "+ob.lastEntry());
		 
		 //returns the lowest entry and also removes from the map
		 System.out.println("value removed from the map:"+ob.pollFirstEntry());
		 
		 //returns the greatest entry and also removes from the map
		 System.out.println("value removed from the map:"+ob.pollLastEntry());
		 Set<Map.Entry<String, String>> citySet = ob.entrySet();
			// using for-each loop in Java 8
			citySet.forEach((m) -> System.out.println("key " + m.getKey() + " value " + m.getValue()));
		

			System.out.println("---- Floor entry-----");

			Map.Entry<String, String> tempMap = ob.floorEntry("II");
			System.out.println(tempMap);

			System.out.println("---- Head Map-----");
			ConcurrentNavigableMap<String, String> map = ob.headMap("MU");
			Set<Map.Entry<String, String>> set = map.entrySet();
			// using for-each loop in Java 8
			set.forEach((m) -> System.out.println("key " + m.getKey() + " value " + m.getValue()));

			System.out.println("---- Higher entry-----");

			tempMap = ob.higherEntry("II");
			System.out.println(tempMap);
	}

}
