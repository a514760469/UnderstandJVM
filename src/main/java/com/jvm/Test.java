package com.jvm;

public class Test {

	private int a;

	static class B {
		int value;
		final int get() {
			return value;
		}
	}

	public void foo() {
	}


	public static void main(String[] args) {
		System.out.println(new Test().a);
	}
}
