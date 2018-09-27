package com.dto;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Flower implements Externalizable{

	String name;
	String color;
	Country country;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name);
		out.writeUTF(color);
		out.writeUTF(country.getName());
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name= in.readUTF();
		color=in.readUTF();
		country= new Country(in.readUTF());
	}
	public Flower(String name, String color, Country country) {
		super();
		this.name = name;
		this.color = color;
		this.country = country;
	}
	public Flower() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
