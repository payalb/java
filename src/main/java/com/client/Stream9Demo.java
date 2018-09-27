package com.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.dto.StudentSemesterMark;
import com.dto.Students;
import com.dto.Target;

//In junit, no sequence defined in which tests may be executed
public class Stream9Demo {

	// Returns 0 or more objects for each object.

	@Test
	public void testFlatMap() {
		long count = Stream.of(1, 2).flatMap(x -> {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i <= x; i++) {
				list.add(i);
			}
			return list.stream();
		}).count();
		assertEquals(5, count);
	}

	/*
	 * Accept elements till condition is true. Once condition is false, stop. May
	 * not process all elements.
	 */
	@Test
	public void testTakeWhile() {
		long count = Stream.of(1, 2, 3, 4, 5, 6, 7).takeWhile(x -> x % 4 != 0).count();
		assertEquals(3, count);
	}

	/*
	 * It is the opposite of takeWhile() method. It drops elements instead of taking
	 * them as long as predicate returns true. Once predicate returns false then
	 * rest of the Stream will be returned.
	 */
	@Test
	public void testDropWhile() {
		long count = Stream.of(1, 2, 3, 4, 5, 6, 7).dropWhile(x -> x % 4 != 0).count();
		assertEquals(4, count);
		// Will return 4,5,6,7 in the stream
	}

	@Test
	public void testIterateOne() {
		List<Integer> list = Stream.iterate(1, t -> t + 1).limit(10).collect(Collectors.toList());
		assertEquals(10, list.size());
		assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), list);
	}

	/* Second argument is for the predicate, which checks the condition */
	@Test
	public void testIterateTwo() {
		List<Integer> list = Stream.iterate(1, x -> x <= 10, t -> t + 1).collect(Collectors.toList());
		assertEquals(10, list.size());
		assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), list);
	}

	/*
	 * This method will check whether the provided element is null or not. If it is
	 * not null, then this method returns the Stream of that element. If it is null
	 * then this method returns empty stream. This method is helpful to deal with
	 * null values in the stream The main advantage of this method is to we can
	 * avoid NullPointerException and null checks everywhere. Usually we can use
	 * this method in flatMap() to handle null values.
	 */
	@Test
	public void testOfNullableOne() {
		List<String> list = Arrays.asList("abc", null, "pqr", null).stream().flatMap(x -> Stream.ofNullable(x))
				.collect(Collectors.toList());
		assertEquals(List.of("abc", "pqr"), list);
	}

	@Test
	public void testOfNullableTwo() {
		Map<String, String> m = new HashMap<>();
		m.put("A", "Apple");
		m.put("B", "Banana");
		m.put("C", null);
		m.put("D", "Dog");
		m.put("E", null);
		List<String> l = m.entrySet().stream().map(e -> e.getKey()).collect(Collectors.toList());
		assertEquals(List.of("A", "B", "C", "D", "E"), l);

		List<String> l2 = m.entrySet().stream().flatMap(e -> Stream.ofNullable(e.getValue()))
				.collect(Collectors.toList());
		assertEquals(List.of("Apple", "Banana", "Dog"), l2);
	}

	@Test
	public void findAverage() {
		List<Target> list = getTargetList();
		list.stream().collect(Collectors.toMap(t -> List.of(t.getYear(), t.getMonth(), t.getName()),
				Function.identity(), (left, right) -> {
					left.setTarget(left.getTarget() + right.getTarget());
					left.setAchieved(left.getAchieved() + right.getAchieved());
					return left;
				}));
	}

	private List<Target> getTargetList() {
		Target t1 = new Target();
		t1.setAchieved(10.00);
		t1.setMonth("8");
		t1.setName("Joey");
		t1.setTarget(50.00);
		t1.setYear("2018");

		Target t2 = new Target();
		t2.setAchieved(100.00);
		t2.setMonth("9");
		t2.setName("Joey");
		t2.setTarget(200.00);
		t2.setYear("2018");

		Target t3 = new Target();
		t3.setAchieved(150.00);
		t3.setMonth("9");
		t3.setName("Fred");
		t3.setTarget(200.00);
		t3.setYear("2018");

		Target t4 = new Target();
		t4.setAchieved(50.00);
		t4.setMonth("9");
		t4.setName("Fred");
		t4.setTarget(20.00);
		t4.setYear("2018");

		List<Target> list = List.of(t1, t2, t3, t4);
		return list;
	}

	/* Collectors.toCollection */
	@Test
	public void testCollect() {
		List<Integer> list = List.of(1, 2, 3).stream().collect(Collectors.toCollection(LinkedList::new));
		assertTrue(list.getClass().getName().contains("LinkedList"));
	}

	/*
	 * Collectors.toMap ToMap collector can be used to collect Stream elements into
	 * a Map instance. To do this, you need to provide two functions: keyMapper
	 * valueMapper keyMapper will be used for extracting a Map key from a Stream
	 * element, and valueMapper will be used for extracting a value associated with
	 * a given key.
	 */
	@Test
	public void testCollectorToMap() {
		List<Target> list = getTargetList();
		List<Target> list1 = list.stream().collect(Collectors
				.toMap(x -> x.getName() + ":" + x.getMonth() + ":" + x.getYear(), Function.identity(), (t, u) -> {
					t.setTarget(t.getTarget() + u.getTarget());
					t.setAchieved(t.getAchieved() + u.getAchieved());
					return t;

				})).values().stream().collect(Collectors.toList());
		assertEquals(3, list1.size());
	}
	
	@Test
	public void testFlatMapTwo() {
		Students st1= new Students("Rajesh", 50,55,60);
		Students st2= new Students("Ravi", 78,76,43);
		Students st3= new Students("Rahul", 54,67,98);
		Students st4= new Students("Rakesh", 23,65,44);
		List<Students> students= List.of(st1,st2,st3,st4);
		
	List<StudentSemesterMark> result=	students.stream().flatMap(x-> {
			StudentSemesterMark sm1= new StudentSemesterMark(x.getName(), "sem1", x.getSem1());
			StudentSemesterMark sm2= new StudentSemesterMark(x.getName(), "sem2", x.getSem2());
			StudentSemesterMark sm3= new StudentSemesterMark(x.getName(), "sem3", x.getSem3());
			return Stream.of(sm1, sm2, sm3);
		}).collect(Collectors.toList());
	assertEquals(12,result.size());
	}
}

