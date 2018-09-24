package com.java.demo.concurrentapi;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentSkipListSetDemo {
	//JDK1.6
	/*ConcurrentSkipListSet is a scalable concurrent set in Java
	 *  which uses ConcurrentSkipListMap internally. ConcurrentSkipListSet
	 *   implements NavigableSet in Java, it is a sorted set just like 
	 *   TreeSet with added feature of being concurrent.ConcurrentSkipListSet 
	 *   does not permit the use of null elements, because null arguments 
	 *   and return values cannot be reliably distinguished from the absence of elements.
	 *   HashSet can only store unique elements. Also, as mentioned internally it uses 
	 *   ConcurrentSkipListMap so when you call add method of ConcurrentSkipListSet 
	 *   it will in turn call putIfAbsent() method of the concurrentMap, that way 
	 *   element is stored only if it is not there already.*/
	public static void main(String[] args) {
		  NavigableSet<String> citySet = new ConcurrentSkipListSet<String>();
	        citySet.add("New Delhi");
	        citySet.add("Mumbai");
	        citySet.add("Chennai");
	        citySet.add("Hyderabad");
	        
	        System.out.println("---- Traversing the set-----");
	        Iterator<String> itr = citySet.iterator();
	        while(itr.hasNext()){
	            System.out.println("Value -  " + itr.next());
	        }
	        
	        //Methods from navigable set
	        System.out.println(citySet.higher("C"));
	        System.out.println(citySet.ceiling("C"));
	        System.out.println(citySet.lower("C"));
	        System.out.println(citySet.lower("H"));
	        System.out.println(citySet.headSet("H",true));
	        System.out.println(citySet.floor("H"));
	        System.out.println(citySet.tailSet("H", true));
	        
	        
	        citySet.remove("Chennai");
	        System.out.println(citySet);
	        /*ConcurrentSkipListSet.clear() method is an in-built function in Java which removes all the elements from this set.*/
	        citySet.clear();
	        System.out.println(citySet);
	}

}
