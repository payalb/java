package com.dto;

import java.io.Serializable;

public class ChildCompany extends Company implements Serializable{

	public ChildCompany(String name, String address) {
		super(name, address);
	}

	public ChildCompany() {
	}
	float budget;

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}
}
