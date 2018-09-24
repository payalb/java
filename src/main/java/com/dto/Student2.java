package com.dto;

import java.util.ArrayList;
import java.util.List;

public class Student2 implements Cloneable {

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
	 * Shallow cloning: It creates a new instance of the class of current object and
	 * initializes all its fields with exactly the contents of the corresponding
	 * fields of this object.
	 */
	@Override
	public Student2 clone() throws CloneNotSupportedException {
		return (Student2) super.clone();
	}
}
