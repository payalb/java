package com.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dto.Student;

public class ReferenceDemo {
	/*
	 * A Student object is created on the heap, and the variable st holds a
	 * reference to that object. As long as the variable st is active, the object
	 * which it points to (Student) will not be garbage collected. Such a reference
	 * is a strong reference. Strong because the object referenced by it cannot be
	 * garbage collected against its will.
	 */
	@Test
	public void strongReference() {
		Student st = new Student();
		st.setMarks(List.of(1.75f, 2.5f, 3.4f));
		st.setName("Payal");
		st = null;// Eligible for gc

	}

	/*
	 * In the case of Weak and Soft, get will return the actual object if still
	 * active — that is, if it is reachable by other objects. In case the object has
	 * been collected, get() will return null.
	 */

	/*
	 * Objects can indeed be knocked out of the heap without their knowledge even
	 * when they are not candidates for garbage collection. These objects are
	 * perfectly good objects which are being referenced and used, but they can
	 * still be removed from the heap. Not because they are hacked, but because they
	 * are weak references. Objects referenced by them may be garbage collected --
	 * if the JVM runs short of memory -- even when they are being used.The most
	 * common use for weak references are read-only caches, where losing an object
	 * is not disastrous. It just means we have to recreate the object, usually back
	 * from the database.If JVM detects an object with only weak references (i.e. no
	 * strong or soft references linked to any object object), this object will be
	 * marked for garbage collection.To do this, you can implement a Map in which
	 * the keys are wrapped in a WeakReference. As soon as GC reclaims the key
	 * object, you can remove the value as well.
	 * 
	 */
	@Test
	public void testWeakReference() {
		Student st = new Student();
		st.setMarks(List.of(1.75f, 2.5f, 3.4f));
		st.setName("Payal");
		ReferenceQueue<Student> refQueue = new ReferenceQueue<>();
		WeakReference<Student> weakReference = new WeakReference<Student>(st, refQueue);
		st = null;
		System.gc();
		//Since st was null, a weak reference cannot prevent object from being garbage collected. 
		assertNull(weakReference.get());
		assertNotNull(refQueue.poll());

	}

	/*
	 * In Soft reference, even if the object is free for garbage collection then
	 * also its not garbage collected, until JVM is in need of memory badly. The
	 * objects gets cleared from the memory when JVM runs out of memory Though st is
	 * available for garbage collection, will only be garbacge collected if jvm runs
	 * out of memory.
	 */
	@Test
	public void testSoftReference() {
		Student st = new Student();
		st.setMarks(List.of(1.75f, 2.5f, 3.4f));
		st.setName("Payal");
		ReferenceQueue<Student> refQueue = new ReferenceQueue<>();
		SoftReference<Student> softReference = new SoftReference<Student>(st, refQueue);
		st = null;
		System.gc();
		//Though st is null, object will not be garbage colelcted until jvm really needs memory,
		assertNotNull(softReference.get());
		assertNull(refQueue.poll());
	}

	/*
	 * Now, Student-type object to which 'st' was pointing earlier is available for
	 * garbage collection. But, this object is kept in 'refQueue' before removing it
	 * from the memory. Phantom always returns null in the get() regardless of
	 * whether the object is still active. In this way, we can pass a
	 * PhantomReference to another object without risking that it will store a new,
	 * hard reference to it. whilst Weak and Soft references are put in the queue
	 * after the object is finalized, Phantom references are put in the queue
	 * before. If for any reason you don’t poll the queue, the actual objects
	 * referenced by Phantom will not be finalized, and you can incur an OutOfMemory
	 * error.
	 * 
	 * The objects which are being referenced by phantom references are eligible for
	 * garbage collection. But, before removing them from the memory, JVM puts them
	 * in a queue called ‘reference queue’ . They are put in a reference queue after
	 * calling finalize() method on them. You can’t retrieve back the objects which
	 * are being phantom referenced. That means calling get() method on phantom
	 * reference always returns null.
	 */
	@Test
	public void testPhantomReference() throws InterruptedException {
		Student st = new Student();
		st.setMarks(List.of(1.75f, 2.5f, 3.4f));
		st.setName("Payal");
		ReferenceQueue<Student> refQueue = new ReferenceQueue<>();
		PhantomReference<Student> phantomRef = null;
		phantomRef = new PhantomReference<Student>(st, refQueue);
		st=null;
		System.gc();
		Thread.sleep(2000);
		// It always returns null.
		// Phantom reference deleted
		assertNull(phantomRef.get());
		assertNull(refQueue.poll());
	}

}
