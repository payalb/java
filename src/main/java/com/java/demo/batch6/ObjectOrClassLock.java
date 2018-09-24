package com.java.demo.batch6;

public class ObjectOrClassLock {

	public static void main(String[] args) {
		ObjectOrClassLock obj1= new ObjectOrClassLock();
		ObjectOrClassLock obj2= new ObjectOrClassLock();
	/*	new Thread (()->{obj1.m1();});
		new Thread (()->{obj2.m1();});*/
		new Thread (()->{obj1.m2();});
		new Thread (()->{obj2.m2();});
	}

	
/*	public synchronized void m1() {
		System.out.println("m1 begin");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m1 end");
	}*/

	public void m2() {
		synchronized(ObjectOrClassLock.class) {
		System.out.println("m1 begin");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m1 end");
		}
	}

}
