package com.java.demo.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanDemo {
	/*An operation during which a processor can simultaneously read a location
	 *  and write it in the same bus operation. This prevents any other processor
	 *   or I/O device from writing or reading memory until the operation is complete.*/
	public static void main(String[] args) {
		AtomicBoolean atomicBoolean = new AtomicBoolean();
		//creates atomic boolean with value false
		boolean oldVal= atomicBoolean.getAndSet(false);
		/*The method compareAndSet() allows you to compare the current value of the
		 *  AtomicBoolean to an expected value, and if current value is equal to the 
		 *  expected value, a new value can be set on the AtomicBoolean if currentValue equals
		 *  expected value*/
		System.out.println(oldVal);
		boolean expectedValue = true;
		boolean newValue      = true;

		boolean wasNewValueSet = atomicBoolean.compareAndSet(
		    expectedValue, newValue); 
		System.out.println(wasNewValueSet);
		System.out.println(atomicBoolean.get());
		/*The compareAndSet() method is atomic, so only a single thread can execute it
		 *  at the same time. Thus, the compareAndSet() method can be used to implemented
		 *   simple synchronizers like locks.*/
		expectedValue = false;
		boolean wasNewValueSet1 = atomicBoolean.compareAndSet(
			    expectedValue, newValue); 
			System.out.println(wasNewValueSet1);
			System.out.println(atomicBoolean.get());
	}

}
