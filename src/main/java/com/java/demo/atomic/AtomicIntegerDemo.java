package com.java.demo.atomic;

import java.util.concurrent.atomic.AtomicInteger;
/*Java 5.0 provides supports for additional atomic operations.
 *  This allows to develop algorithm which are non-blocking algorithm,
 *   e.g. which do not require synchronization, but are based on low-level
 *    atomic hardware primitives such as compare-and-swap (CAS). A compare-and-swap
 *     operation check if the variable has a certain value and if it has this value 
 *     it will perform this operation. 
Non-blocking algorithm are usually much faster then blocking algorithms as the
 synchronization of threads appears on a much finer level (hardware).
  the atomic classes make heavy use of compare-and-swap (CAS), an atomic 
  instruction directly supported by most modern CPUs */
public class AtomicIntegerDemo {

	//Like this we have AtomicLong as well
	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger();
		System.out.println(atomicInteger.getAndAdd(10));
		System.out.println(atomicInteger.addAndGet(10));
		System.out.println(atomicInteger.incrementAndGet());
		System.out.println(atomicInteger.getAndDecrement());
		atomicInteger.set(40);
		System.out.println(atomicInteger.get());
	}

}
