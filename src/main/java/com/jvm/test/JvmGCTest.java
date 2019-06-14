package com.jvm.test;

public class JvmGCTest {
	
	public static void test() {
		test();
	}
	public static void main(String[] args) {
		test();
	}
}
