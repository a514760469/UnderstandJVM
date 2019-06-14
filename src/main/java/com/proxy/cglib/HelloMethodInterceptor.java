package com.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class HelloMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("Before: " + method.getName());
		Object result = proxy.invokeSuper(obj, args);
		System.out.println("After: " + method.getName());
		return result;
	}

}
