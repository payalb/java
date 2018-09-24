package com.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MathDemo {

	@Test
	public void test1() {
		double nextDown = Math.nextDown(Double.NaN);
		assertEquals(Double.NaN, nextDown, 0.0);

		double nextDown2 = Math.nextDown(Double.NEGATIVE_INFINITY);
		assertEquals(Double.NEGATIVE_INFINITY, nextDown2, 0.0);

		double nextDown3 = Math.nextDown(0);
		assertEquals(-Double.MIN_VALUE, nextDown3, 0.1);

		double nextDown4 = Math.nextDown(2);
		assertEquals(1.9999998807907104, nextDown4, 0.0001);

		double nextDown5 = Math.nextDown(-2);
		assertEquals(-2.000, nextDown5, 0.0001);

	}

	@Test
	public void test2() {

		int floorDiv = Math.floorDiv(45, 4);
		assertEquals(11,floorDiv);
		int x = -45; // 10101101
		int y = 4;  // 00000100
		int r = x / y;
		assertEquals(-11,r);
		assertEquals(-41, x ^ y); //     10101001
		assertTrue(r * y != x);
		int floorDiv2 = Math.floorDiv(-45, 4);
		assertEquals(-12,floorDiv2);
	}

}
