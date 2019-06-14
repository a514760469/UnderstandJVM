package com.algorithm;

public class Test {
	
	public static void main(String[] args) {
		// 	1010
		int a = 10;
		System.out.println(a >> 2);
		System.out.println(a << 2);
		
		
		String binary = Integer.toBinaryString(a >> 2);
		System.out.println(binary);
	}
	
}
