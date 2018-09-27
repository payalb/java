package com.dto;

public class Product {

	private int price;
	private String name;
	
	public Product( String name,int price) {
		super();
		this.price = price;
		this.name = name;
	}

	public Product(int i, String string) {
		this.price = i;
		this.name = string;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Product [price=" + price + ", name=" + name + "]";
	}
}