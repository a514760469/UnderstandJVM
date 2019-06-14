package com.jvm.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;

import static java.lang.invoke.MethodHandles.lookup;

public class MethodHandleInvoke {
	
	class GrandFather {
		void thinking() {
			System.out.println("I am grandfather");
		}
	}
	
	class Father extends GrandFather {
		void thinking() {
			System.out.println("I am Father");
		}
	}
	
	class Son extends Father {
		void thinking() {
			// 调用GrandFather的thinking
			MethodType methodType = MethodType.methodType(void.class);	
			try {
				MethodHandle methodHandle = 
						lookup().findSpecial(GrandFather.class, "thinking", methodType, getClass());
				methodHandle.invoke(this);
			} catch (Throwable e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		(new MethodHandleInvoke().new Son()).thinking();
	}
}


