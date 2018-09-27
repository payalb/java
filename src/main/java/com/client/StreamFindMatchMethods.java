package com.client;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamFindMatchMethods {

	@Test
	public void test1() {
		Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
		Optional<String> anyElement = stream.findAny();
		assertEquals("b", anyElement.get());

	}

	@Test
	public void test2() {
		List<String> elements = Stream.of("a", "b", "c").filter(element -> element.contains("b"))
				.collect(Collectors.toList());
		Optional<String> anyElement = elements.stream().findAny();
		Optional<String> firstElement = elements.stream().findFirst();
		assertEquals("b", anyElement.get());
		assertEquals("b", firstElement.get());

	}

	// Skip method:to create a new stream of the existing one without few elements
	// the skip() method should be used:
	@Test(expected = IllegalStateException.class)
	public void test3() {
		IntStream s = IntStream.of(1, 2, 3, 4, 5, 6);
		IntStream s1 = s.skip(4);
		// Will skip first 4 elements
		assertEquals(2, s1.count());
		// After calling terminal operation, if we try to again reuse stream,
		// IllegalStateException
		assertEquals(5, s1.findFirst().getAsInt());
		assertEquals(6, s1.max());
	}

	@Test
	public void test4() {
		IntStream s = IntStream.of(1, 2, 3, 4, 5, 6);
		IntStream s1 = s.skip(4);
		// Will skip first 4 elements
		// Only one terminal operation can be used per stream.
		assertArrayEquals(new int[] { 5, 6 }, s1.toArray());

	}

	@Test
	public void test5() {

		List<String> list = Arrays.asList("abc1", "abc2", "abc3");

		Stream<String> stream = list.stream().filter(element -> {
			wasCalled();
			return element.contains("2");
		});
		
		//Since no terminary operator, filter not invoked.
		assertEquals(0, counter);

	}

	int counter = 0;

	private void wasCalled() {
		counter++;
	}

	@Test
	public void test6() {
		List<String> list = Arrays.asList("abc1", "abc2", "abc3");
		Optional<String> stream = list.stream().filter(element -> {
		    return element.contains("2");
		}).map(element -> {
		    return element.toUpperCase();
		}).findFirst();
		assertEquals("ABC2", stream.get());
	}
	
	@Test
	public void test7() {
		Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
		boolean anyElement = stream.anyMatch(x-> x.contains("b"));
		assertTrue(anyElement);

	}
	
	@Test
	public void test8() {
		boolean val = Stream.of(10,20,30,40,50).allMatch(x-> x%10==0);
		assertTrue(val);

	}
	
	@Test
	public void test9() {
		boolean val = Stream.of(10,20,30,40,50).noneMatch(x-> x%10!=0);
		assertTrue(val);

	}
}
