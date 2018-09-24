package com.client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThreadException_CompletableFuture {
	
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		CompletableFuture<Object> cf= CompletableFuture.supplyAsync(()-> {
				throw new RuntimeException("Test exception handling");
		}).exceptionally(x-> new Result(x.getMessage()));
		
		System.out.println(cf.get());
	}

	
}

class Result{
	public Result(String message2) {
		this.message= message2;
	}

	String message;

	@Override
	public String toString() {
		return "Result [message=" + message + "]";
	}
}