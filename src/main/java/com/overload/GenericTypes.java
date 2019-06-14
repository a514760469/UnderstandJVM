package com.overload;

import java.util.List;

public class GenericTypes {
	
	public static String method(List<String> list) {
		System.out.println("List<String> list");
		return "";
	}
	
//	public static int method(List<Integer> list) {
//		System.out.println("List<Integer> list");
//		return 0;
//	}
	public static void main(String[] args) {
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 128;// 127
		Integer f = 128;
		Long g = 3L;
		/*
		 * 127		1111111			
		 * 			10000000
		 */
		System.out.println(c == d);// true
		System.out.println(e == f);// false
		System.out.println(c == (a + b));// true
		System.out.println(c.equals(a + b));// true
		System.out.println(g == (a + b));// true
		System.out.println(g.equals(a + b));// false
		
	}
}
