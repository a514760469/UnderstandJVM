package com.jvm.compile;

public class RunTimeCompileTest {
	
	public static final int NUM = 15000;
	
	public static int doubleValue(int i) {
		// 空循环用于演示JIT
		for(int j = 0; j < 100000; j++);
		return i * 2;
	}
	
	public static long calcSum() {
		long sum = 0;
		for (int i = 1; i <= 100; i++ ) {
			sum += doubleValue(i);
		}
		return sum;
	}
	
	/**
	 * -XX:+PrintCompilation 虚拟机即时编译时将被编译成本地代码的方法名称打印
	 * -XX: +PrintInlining 输出方法内联信息
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < NUM; i++) {
			calcSum();
		}
	}
}
