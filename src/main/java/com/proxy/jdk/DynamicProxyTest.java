package com.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
	/**
	 * jdk动态代理需要有一个接口
	 */
	interface IHello {
		void sayHello(); 
	}

	static class Hello implements IHello {

		@Override
		public void sayHello() {
			System.out.println("hello world!");
		}
		
	}
	
	public static class DynamicProxy implements InvocationHandler {
		
		Object originalObj;
		
		Object bind(Object originalObj) {
			this.originalObj = originalObj;
			return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), 
					originalObj.getClass().getInterfaces(), this);
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("welcome");
			Object object = method.invoke(originalObj, args);
			System.out.println("可以做一些事情");
			return object;
		}
	}
	
	public static void main(String[] args) {
		
//		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");// 代理类写入磁盘， 在项目根目录com/proxy下
		IHello hello = (IHello) new DynamicProxy().bind(new Hello());
		hello.sayHello();
		System.out.println(hello.getClass().getName());
	}
	
}
