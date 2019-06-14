package com.jvm.loadclass;

public class SuperClass {
	static {
		System.out.println("superClass init !");
	}
	public static int value = 123;
	
	public static final String HELLOWORLD = "hello world!";
}

class SubClass extends SuperClass {
	static {
		System.out.println("subClass init !");
	}
}
class ConstClass {
	
	static {
		System.out.println("ConstClass init !");
	}
	public static final String HELLOWORLD = "hello world!";
}


