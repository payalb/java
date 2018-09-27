package com.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Collectors;

import org.junit.Test;

import com.dto.Product;

/*Collectors are a class an implementations of Collector that implement various 
 * useful reduction operations, such as accumulating elements into collections,
 *  summarizing elements according to various criteria, etc.*/
public class StreamCollectorDemo {

	@Test
	public void test1() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		double averagePrice = productList.stream().collect(Collectors.averagingInt(Product::getPrice)).doubleValue();
		assertEquals(17.2, averagePrice, 0);
	}

	@Test
	public void test2() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		int totalPrice = productList.stream().collect(Collectors.summingInt(Product::getPrice)).intValue();
		assertEquals(86, totalPrice);
	}

	@Test
	public void test3() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		Product productWithMaxPrice = productList.stream()
				.collect(Collectors.maxBy((x, y) -> x.getPrice() - y.getPrice())).get();
		assertEquals(23, productWithMaxPrice.getPrice());
	}

	/*
	 * The joiner() method can have from one to three parameters (delimiter, prefix,
	 * suffix). The handiest thing about using joiner() – developer doesn’t need to
	 * check if the stream reaches its end to apply the suffix and not to apply a
	 * delimiter. Collector will take care of that.
	 */
	@Test
	public void test4() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		String listToString = productList.stream().map(Product::getName).collect(Collectors.joining(", ", "[", "]"));
		assertEquals("[potatoes, orange, lemon, bread, sugar]", listToString);
	}

	/*
	 * It is also easy to extract from this object separate values for count, sum,
	 * min, average by applying methods getCount(), getSum(), getMin(),
	 * getAverage(), getMax(). All these values can be extracted from a single
	 * pipeline.
	 */
	@Test
	public void test5() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		IntSummaryStatistics statistics = productList.stream().collect(Collectors.summarizingInt(Product::getPrice));
		assertEquals(23, statistics.getMax());
		assertEquals(17.2, statistics.getAverage(), 0);
		assertEquals(5, statistics.getCount());
	}

	// grouping products as per price
	@Test
	public void test6() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		Map<Integer, List<Product>> map = productList.stream().collect(Collectors.groupingBy(Product::getPrice));
		assertEquals(3, map.size());
	}

	// grouping products as per value, products wit price <20 in one group. 20-40 in
	// other group
	@Test
	public void test7() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		Map<Boolean, List<Product>> map = productList.stream()
				.collect(Collectors.partitioningBy(x -> x.getPrice() < 20));
		assertEquals(3, map.get(true).size());
	}

//get unmodifiable set
	/*
	 * CollectingAndThen() method first collects the elements of type T of Stream<T>
	 * using the Collector<T,A,R> passed to it as the first parameter. As a result
	 * of applying the collector, stream elements are collected into an object of
	 * type R. Using the Function<R,RR> instance passed as the second parameter, the
	 * collected object of type R is then transformed to an object of type RR. This
	 * object of type RR is the final object/value returned by the collectingAndThen
	 * collector.
	 */
	@Test
	public void test8() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		productList.stream().collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
	}

	/*1.Find the product with the maximum price for which we want to use the maxBy collector.
	2. The output of the maxBy collector being an Optional value, we want to check whether a value is present and then print the name
*/
	@Test
	public void test9() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		Product prod=productList.stream().max((x,y)-> x.getPrice()-y.getPrice()).orElse(null);
		assertNotNull(prod);
	}
	
	
	@Test
	public void test10() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		String prod=productList.stream().collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Product::getPrice)), x-> x.isPresent()?x.get().getName():null));
		assertNotNull(prod);
	}
	
	//Custom Collector
	@Test
	public void test11() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		HashMap<Integer, List<Product>> prod=productList.stream().collect(Collector.of(new Supplier<HashMap<Integer, List<Product>>>() {

			@Override
			public HashMap<Integer, List<Product>> get() {
				return new HashMap<Integer,  List<Product>>();
			}},new BiConsumer<HashMap<Integer,  List<Product>>, Product>() {
			@Override
			public void accept(HashMap<Integer,  List<Product>> t, Product u) {
				List<Product> l= null;
				if(t.get(u.getPrice())==null) {
				l=new ArrayList<>();
				
				}else {
					l=t.get(u.getPrice());
				}
				l.add(u);
				t.put(u.getPrice(), l);
			}
			
		}, new BinaryOperator<HashMap<Integer,  List<Product>>> () {

			@Override
			public HashMap<Integer, List<Product>> apply(HashMap<Integer, List<Product>> t,
					HashMap<Integer, List<Product>> u) {
				t.putAll(u);
				return t;
			}
		},Characteristics.IDENTITY_FINISH));
		
		assertEquals(3,prod.size());
	}
	// @Test
	public void test21() {
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));
		// Custom collector
		Collector<Product, ?, LinkedList<Product>> toLinkedList = Collector.of(LinkedList::new, LinkedList::add,
				(first, second) -> {
					first.addAll(second);
					return first;
				});

		LinkedList<Product> linkedListOfPersons = productList.stream().collect(toLinkedList);
		linkedListOfPersons.forEach(System.out::println);
	}
}
