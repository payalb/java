package com.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.dto.Country;
import com.dto.Flower;

public class ExternalizableDemo {

	@Test
	public void test1() throws FileNotFoundException, IOException, ClassNotFoundException {
		Flower flower = new Flower("Daisy","blue", new Country("India"));
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("productInfo.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("productInfo.txt"));) {
			oos.writeObject(flower);
			oos.flush();
			Flower flowerObj = (Flower) ois.readObject();
			assertEquals( flower.getName(),flowerObj.getName());
			assertEquals( flower.getColor(),flowerObj.getColor());
			assertEquals( flower.getCountry().getName(),flowerObj.getCountry().getName());
		}
	}
}
