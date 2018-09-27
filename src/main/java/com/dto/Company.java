package com.dto;

public class Company {

	private String name;
	public Company() {
		super();
	}
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Company(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
}
