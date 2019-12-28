package com.java8.stream.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class LambdaCollection {
	
	private List<String> list1 = new ArrayList<>();
	private List<String> list2 = new ArrayList<>();

	@Before
	public void before() {
		list1.add("1111");
	    list1.add("2222");
	    list1.add("3333");
	    list2.add("3333");
	    list2.add("4444");
	    list2.add("5555");
	}



	/**
	 * 交集
	 */
	@Test
	public void test1() {
		List<String> list = list1.stream().filter(item -> list2.contains(item)).collect(Collectors.toList());
		list.forEach(System.out::println);
	}

	/**
	 * 并集
	 */
	@Test
	public void test2() {
		list1.addAll(list2);
		list1.stream().distinct().forEach(System.out::println);
	}

	@Test
	public void testNormal() {
//		list1.containsAll(list2);
		list1.retainAll(list2);
		System.out.println(list1);
	}
}



