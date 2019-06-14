package com.proxy.cglib;

import org.junit.Test;

import net.sf.cglib.proxy.Enhancer;

public class Client {
	public static void main(String[] args) {
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Hello.class); // 继承被代理类
		enhancer.setCallback(new HelloMethodInterceptor());// 设置回调
		Hello hello = (Hello) enhancer.create();// 生成代理类对象
		hello.sayHello();
		
	}
	
	@Test
	public void test()  {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(BookieService.class);
		enhancer.setCallback(new HelloMethodInterceptor());
//		Class createClass = enhancer.createClass();
//		Method[] methods = createClass.getDeclaredMethods();
//		for (Method method : methods) {
//			try {
//				Object result = method.invoke(createClass, "ceshi");
//				System.out.println(result);
//			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//				e.printStackTrace();
//			}
//		}
		Object obj = enhancer.create();
		System.out.println(obj instanceof BookieService);
		if(obj instanceof BookieService) {
			String save = ((BookieService) obj).save("vesdsaw");
			System.out.println(save);
		}
		
	}
	
}




