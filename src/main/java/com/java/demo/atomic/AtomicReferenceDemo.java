package com.java.demo.atomic;

import java.util.concurrent.atomic.AtomicReference;

import com.java.demo.dto.Person;

public class AtomicReferenceDemo {

	//For objects
	public static void main(String[] args) {
		AtomicReference<Person> p= new AtomicReference<>(new Person(1, "Payal", 30));
		p.set(new Person(2,p.get().getName()+"updated", 31));
		  Thread t1 = new Thread(new Runnable(){
	            @Override
	            public void run() {
	                for(int i=1 ; i<=3 ; i++){
	                    p.set(new Person(3, "p1",p.get().getAge()+10));
	                    System.out.println("Atomic Check by first thread: "+Thread.currentThread().getName()+" is "+p.get().getAge());
	                }
	            }
	        });
	        Thread t2 = new Thread(new Runnable(){
	            @Override
	            public void run() {
	                Person per = p.get();
	                for(int i=1 ; i<=3 ; i++){
	                    System.err.println(p.get().equals(per)+"_"+per.getAge()+"_"+p.get().getAge());
	                    p.compareAndSet(per, new Person(4, "p2",p.get().getAge()+10));
	                    System.out.println("Atomic Check by second thread : "+Thread.currentThread().getName()+" is "+p.get().getAge());
	                }
	            }
	        });
	        t1.start();
	        t2.start();
	        try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        try {
				t2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        System.out.println("Final value: "+p.get().getAge());
	}

}
