package com.dto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Flower2 implements Serializable{

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

	public Flower2(String name, String color, Country country) {
		super();
		this.name = name;
		this.color = color;
		this.country = country;
	}
	public Flower2() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(color);
		out.writeUTF(name);
		out.writeUTF(country.getName());
	}
	
	 private void readObject(ObjectInputStream ois)
		        throws ClassNotFoundException, IOException{
					color= ois.readUTF();
					name=ois.readUTF();
					country= new Country(ois.readUTF());
		 
	 }

}
