package com.dto;

import java.util.ArrayList;
import java.util.List;

public class Student4 implements Cloneable {

	private String name;
	private List<Float> marks = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Float> getMarks() {
		return marks;
	}

	public void setMarks(List<Float> marks) {
		this.marks = marks;
	}

	/*
	 * Deep cloning: A deep copy occurs when an object is copied along with the objects to which it refers.
	 */
	@Override
	public Student4 clone() throws CloneNotSupportedException {
		Student4 obj= new Student4();
		List<Float> newList= new ArrayList<>(obj.getMarks());
		obj.setMarks(newList);
		obj.setName(name);
		return obj;
	}
}
