package com.dto;

public class Students {

	private String name;
	private int sem1;
	private int sem2;
	private int sem3;

	public Students(String name, int sem1, int sem2, int sem3) {
		super();
		this.name = name;
		this.sem1 = sem1;
		this.sem2 = sem2;
		this.sem3 = sem3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSem1() {
		return sem1;
	}

	public void setSem1(int sem1) {
		this.sem1 = sem1;
	}

	public int getSem2() {
		return sem2;
	}

	public void setSem2(int sem2) {
		this.sem2 = sem2;
	}

	public int getSem3() {
		return sem3;
	}

	public void setSem3(int sem3) {
		this.sem3 = sem3;
	}
}
