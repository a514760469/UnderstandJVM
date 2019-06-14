package com.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		ClassLoader myLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				try {
					System.err.println("loadClass: " + name);
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					InputStream is = getClass().getResourceAsStream(fileName);
					if(is == null) {
						return super.loadClass(name);
					}
				
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (IOException e) {
					e.printStackTrace();
					throw new ClassNotFoundException();
				}
			}
		};
		System.out.println(ByteUtils.class.getClassLoader());
		Object obj = myLoader.loadClass("com.jvm.classloader.ClassLoaderTest").newInstance();
		System.out.println(obj);
		System.out.println(obj instanceof com.jvm.classloader.ClassLoaderTest);
		Object obj2 = new ClassLoaderTest();
		System.out.println(obj2 instanceof com.jvm.classloader.ClassLoaderTest);
		System.out.println(obj.getClass().getClassLoader());
		
//		System.out.println(new ClassLoaderTest() instanceof ClassLoaderTest);
	}
}
