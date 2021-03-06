package com.jvm.oom;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 方法区溢出
 * @author zhanglifeng
 * 2019年3月28日-下午1:17:22
 */
public class JavaMethodAreaOOM {

	static class OOMObject {
		
	}
	/**
	 * -XX:PermSize=10M -XX:MaxPermSize=10M
	 * @param args
	 */
	public static void main(String[] args) {
		
		while (true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {

				@Override
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					return proxy.invoke(obj, args);
				}
				
			});
			enhancer.create();
		}
	}
}
