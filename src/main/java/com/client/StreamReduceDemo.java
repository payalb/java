package com.client;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.dto.Product;

public class StreamReduceDemo {

	/*
	 * IntStream reduce(int identity, IntBinaryOperator op) performs a reduction on
	 * the elements of this stream, using the provided identity value and an
	 * associative accumulation function, and returns the reduced value.This is a
	 * terminal operation i.e, it may traverse the stream to produce a result or a
	 * side-effect. After the terminal operation is performed, the stream pipeline
	 * is considered consumed, and can no longer be used.
	 */
	@Test
	public void test1() {
		IntStream stream = IntStream.of(2, 3, 4, 5, 6);

		// Using IntStream reduce
		// (int identity, IntBinaryOperator op)
		int answer = stream.reduce(0, (num1, num2) -> (num1 + num2));

		assertEquals(20, answer);
	}

	@Test
	public void test2() {
		IntStream stream = IntStream.of(2, 3, 4, 5, 6);
		int answer = stream.reduce(1, (num1, num2) -> (num1 + num2));
		assertEquals(21, answer);
	}

	@Test
	public void test3() {
		IntStream stream = IntStream.of(2, 3, 4, 5, 6);
		int answer = stream.reduce(0, (num1, num2) -> (num1 + num2) * 2);
		// 0+2*2=4 (4+3)2 (14+4)2 (36+5)2 (82+6)2
		assertEquals(176, answer);
	}

	@Test
	public void test4() {
		IntStream stream = IntStream.of(4, 10);
		int answer = stream.reduce(0, (num1, num2) -> (num1 + num2) - 2 * (num1 - num2));
		// 12 (12+10)-2(2)
		assertEquals(18, answer);
	}

	/*
	 * The API has many terminal operations which aggregate a stream to a type or to
	 * a primitive, for example, count(), max(), min(), sum(), but these operations
	 * work according to the predefined implementation. And what if a developer
	 * needs to customize a Stream’s reduction mechanism? There are two methods
	 * which allow to do this – the reduce() and the collect() methods.
	 */
	@Test
	public void test5() {
		IntStream stream = IntStream.of(4, 10);
		OptionalDouble answer = stream.average();
		// 12 (12+10)-2(2)
		assertEquals(7, answer.getAsDouble(), 0);
	}

	
	@Test
	public void test6() {
		IntStream stream = IntStream.of(4, 10);
		OptionalInt answer = stream.reduce((x,y)-> x-y);
		assertEquals(-6,answer.getAsInt());
	}

	@Test
	public void test7() {
		int x=IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
		//1,2,3: stream
		//10+1 +2+3
		assertEquals(16,x);
	}
	
	//reduce for parallel streams
	@Test
	public void test8() {
		int x=Stream.of(1,2,3).parallel().reduce(10, ((a, b) -> a + b), ((a,b)-> a+b));
		//1,2,3: stream
		//10+1 10+2 10+3 => 11+12+13
		assertEquals(36,x);
	}

}
