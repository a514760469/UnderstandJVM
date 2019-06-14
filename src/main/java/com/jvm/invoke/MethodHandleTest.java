package com.jvm.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {
	static class ClassA {
		public void println(String s) {
			System.out.println(s);
		}
	}
	
	private static MethodHandle getPrintlnMH(Object reveiver) throws Exception {
		MethodType methodType = MethodType.methodType(void.class, String.class);
		return MethodHandles.lookup().findVirtual(reveiver.getClass(), "println", methodType).bindTo(reveiver);
	}
	
	public static void main(String[] args) throws Throwable {
		System.out.println(System.currentTimeMillis() % 2 == 0);
		Object obj = (System.currentTimeMillis() % 2 == 0) ? System.out : new ClassA();
		getPrintlnMH(obj).invokeExact("icyfenix");
	}
}
