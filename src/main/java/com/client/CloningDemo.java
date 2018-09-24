package com.client;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dto.Student;
import com.dto.Student2;
import com.dto.Student3;
import com.dto.Student4;

public class CloningDemo {
	/*
	 * if the class of this object does not implement the interface Cloneable, then
	 * a CloneNotSupportedException is thrown. Note that all arrays are considered
	 * to implement the interface Cloneable and that the return type of the clone
	 * method of an array type T[] is T[] where T is any reference or primitive
	 * type.
	 */
	@Test(expected = CloneNotSupportedException.class)
	public void cloningOne() throws CloneNotSupportedException {
		Student st = new Student();
		st.setName("Payal");
		st.setMarks(List.of(12.2f, 34.7f, 65.3f));
		Student st1 = st.clone();

	}

	/*
	 * Object cloning refers to creation of exact copy of an object. It creates a
	 * new instance of the class of current object and initializes all its fields
	 * with exactly the contents of the corresponding fields of this object.
	 */
	@Test
	public void cloningTwo() throws CloneNotSupportedException {
		Student[] starr = new Student[5];
		Student st = new Student();
		st.setName("Payal");
		st.setMarks(List.of(12.2f, 34.7f, 65.3f));
		starr[0] = st;
		Student[] starr1 = new Student[5];
		starr1 = starr.clone();
		assertNotEquals(starr.hashCode(), starr1.hashCode());
		assertEquals(starr[0].hashCode(), starr1[0].hashCode());
	}

	@Test
	public void shallowCloning() throws CloneNotSupportedException {
		Student2 st = new Student2();
		st.setName("Payal");
		st.setMarks(List.of(12.2f, 34.7f, 65.3f));
		Student2 st1 = st.clone();
		assertNotEquals(st1.hashCode(), st.hashCode());
		assertEquals(st1.getMarks().hashCode(), st.getMarks().hashCode());
	}

	
	@Test
	public void deepCloningOne() throws CloneNotSupportedException {
		Student3 st = new Student3();
		st.setName("Payal");
		ArrayList<Float> list= new ArrayList<>();
		list.add(12.5f);
		st.setMarks(list);
		Student3 st1 = st.clone();
		assertNotEquals(st1.hashCode(), st.hashCode());
		assertNotEquals(st1.getMarks().hashCode(), st.getMarks().hashCode());
	}

	@Test
	public void deepCloningTwo() throws CloneNotSupportedException {
		Student4 st = new Student4();
		st.setName("Payal");
		ArrayList<Float> list= new ArrayList<>();
		list.add(12.5f);
		st.setMarks(list);
		Student4 st1 = st.clone();
		assertNotEquals(st1.hashCode(), st.hashCode());
		assertNotEquals(st1.getMarks().hashCode(), st.getMarks().hashCode());
	}

}
