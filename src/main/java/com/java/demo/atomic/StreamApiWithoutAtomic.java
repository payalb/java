package com.java.demo.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

//Returns Invalid values
public class StreamApiWithoutAtomic {
	int count = 0;

	void increment() {
	    count = count + 1;
	}
	public static void main(String[] args) {
		StreamApiWithoutAtomic obj= new StreamApiWithoutAtomic();
		ExecutorService executor = Executors.newFixedThreadPool(2);

		
		IntStream.range(0, 10000)
		    .forEach(i -> executor.execute(()->obj.increment()));

		try {
			executor.awaitTermination(2000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(obj.count);  // 9965| 9995

	}

}
