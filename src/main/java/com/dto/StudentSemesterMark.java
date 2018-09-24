package com.dto;

public class StudentSemesterMark {
	
	String name;
	String semester;
	int semmark;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getSemmark() {
		return semmark;
	}
	public void setSemmark(int semmark) {
		this.semmark = semmark;
	}
	public StudentSemesterMark(String name, String semester, int semmark) {
		super();
		this.name = name;
		this.semester = semester;
		this.semmark = semmark;
	}

}
