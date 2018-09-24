package com.dto;

import java.util.ArrayList;
import java.util.List;

public class Student3 implements Cloneable {

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
	public Student3 clone() throws CloneNotSupportedException {
		Student3 obj= new Student3();
		List<Float> newList= new ArrayList<>();
		/*We must manually clone every object. 
		 * Clone method does not necessarily work
		 */
		List<Float> list= obj.getMarks();
		for(Float f: list) {
			newList.add(f);
		}
		obj.setMarks(newList);
		obj.setName(name);
		return obj;
	}
}
