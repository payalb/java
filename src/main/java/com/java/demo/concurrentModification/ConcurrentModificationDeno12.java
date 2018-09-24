package com.java.demo.concurrentModification;

import java.util.stream.Stream;

public class ConcurrentModificationDeno12 {
	 /*using java 8 stream api*/
	public static void main(String[] args) {
	Stream.of("Java", "PHP","SQL", "Angular 2").
	filter(x->(!"Sql".equalsIgnoreCase(x))).forEach(System.out::println);
		
	}
}
