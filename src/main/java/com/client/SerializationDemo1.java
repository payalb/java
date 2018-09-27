package com.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.dto.ChildCompany;
import com.dto.Company;
import com.dto.Company1;
import com.dto.Country;
import com.dto.Flower2;
import com.dto.Product;
import com.dto.Product1;
import com.dto.Product2;
import com.dto.Product3;

public class SerializationDemo1 {

	/*
	 * If you try to serialize any java object and the class does not implement
	 * Serializable interface, it will throw NotSerializableException
	 */
	 @Ignore
	@Test(expected = NotSerializableException.class)
	public void test1() throws FileNotFoundException, IOException {
		Product product1 = new Product("Key Ring", 45);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("productInfo.txt"))) {
			oos.writeObject(product1);
			oos.flush();
		}
	}

	/*
	 * Product1 implements Serializable, when writing to a file, if a file does not
	 * exist, it will create one. So successful execution of this test case
	 */
	 @Ignore
	@Test
	public void test2() throws IOException, ClassNotFoundException {
		Product1 product1 = new Product1("Key Ring", 45);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("productInfo.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("productInfo.txt"));) {
			oos.writeObject(product1);
			oos.flush();
			Product1 obj = (Product1) ois.readObject();
			/*
			 * if(!obj.getName().equals("Key Ring")){ throw new Exception(); }
			 */
			Assert.assertEquals(product1.getName(), obj.getName());
			Assert.assertEquals("The two values u r comparing are unequal", product1.getPrice(), obj.getPrice());
			Assert.assertNotSame(product1, obj);
		}

	}

	/*
	 * If we have an association relationship with a class that is not serializable,
	 * it will again throw an exception
	 */
	 @Ignore
	@Test(expected = NotSerializableException.class)
	public void test3() throws IOException, ClassNotFoundException {
		Product2 product1 = new Product2("Key Ring", 45);
		Company company = new Company("Johnson", "US");
		product1.setCompany(company);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("productInfo.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("productInfo.txt"));) {
			oos.writeObject(product1);
			oos.flush();
			Product1 obj = (Product1) ois.readObject();

		}

	}
	 @Ignore
	@Test
	public void test4() throws IOException, ClassNotFoundException {
		Product3 product1 = new Product3("Key Ring", 45);
		Company1 company = new Company1("Johnson", "US");
		product1.setCompany(company);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("productInfo.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("productInfo.txt"));) {
			oos.writeObject(product1);
			oos.flush();
			Product3 obj = (Product3) ois.readObject();
			Assert.assertEquals(product1.getName(), obj.getName());
			Assert.assertEquals(product1.getPrice(), obj.getPrice());
			Assert.assertEquals(product1.getCompany(), obj.getCompany());
		}

	}

	/*
	 * If we have a “is-a” relationship with a class. Parent is not serializable and
	 * i try to serialize a child object: It saves only child properties
	 */
	 @Ignore
	@Test
	public void test5() throws IOException, ClassNotFoundException {
		ChildCompany company = new ChildCompany("Johnson", "US");
		company.setBudget(834787534.43f);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("productInfo.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("productInfo.txt"));) {
			oos.writeObject(company);
			oos.flush();
			ChildCompany obj = (ChildCompany) ois.readObject();
			assertEquals(834787534.43f, obj.getBudget(), 0);
			assertNull(obj.getName());
			assertNull(obj.getAddress());
		}

	}
	
	 @Test
		public void test6() throws IOException, ClassNotFoundException {
		 Flower2 obj = new Flower2("Lotus", "Pink",new Country("India"));
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("productInfo.txt"));
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream("productInfo.txt"));) {
				oos.writeObject(obj);
				oos.flush();
				Flower2 obj1= (Flower2) ois.readObject();
				assertEquals(obj.getColor(), obj1.getColor());
				assertEquals(obj.getName(), obj1.getName());
				assertEquals(obj.getCountry().getName(), obj1.getCountry().getName());
			}
			

		}
	 
/*	@Test
	public void test6() throws IOException, ClassNotFoundException {
		Company1 company = new Company1("Johnson", "US");
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("productInfo.txt"));
				) {
			oos.writeObject(company);
			oos.flush();
			
		}
		

	}
	@Test
	public void test7() throws IOException, ClassNotFoundException {
		Company1 company = new Company1("Johnson", "US");
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("productInfo.txt"));) {
			Company1 obj= (Company1) ois.readObject();
			assertEquals(company.getName(), obj.getName());
		}
		

	}*/
}

//IO classes InputStreamOutputStream	| ReaderWriter

//FileReader FileWriter  "/n"
//BufferedReader BufferedWriter: it uses buffer (faster)+ newline()
//PrintWriter: primitive data type printInt(), printChar() printBoolean()
//Employee Object : ObjectInputStream , ObjectOutputStream
//converting java object to bytes : transferred over the network/ save it the file
