package com.dto;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private String name;
	private List<Float> marks= new ArrayList<>();
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
	
	public Student clone() throws CloneNotSupportedException {
		return (Student) super.clone();
	}
	
	@Override
	public void finalize() {
		System.out.println("Finalize called!");
		
	}
}
