package com.jvm;

public class Test {
	static class B {
		int value;
		final int get() {
			return value;
		}
	}
	
	public void foo() {
	}
	
	private int a;
	
	public static void main(String[] args) {
		System.out.println(new Test().a);
	}
}
