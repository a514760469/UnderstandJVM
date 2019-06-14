package com.jvm.classloader;

public class ClassNotFoundTest {
	
	public static void main(String[] args) {
//		System.out.println(ClassNotFoundTest.class.getClassLoader());
		try {          
            //printing ClassLoader of this class
//            System.out.println("ClassLoaderTest.getClass().getClassLoader() : "
//                                 + ClassLoaderTest.class.getClassLoader());
            
            //trying to explicitly load this class again using Extension class loader
            Class.forName("com.jvm.classloader.ClassLoaderTest", true, 
            		ClassNotFoundTest.class.getClassLoader().getParent());
            
		} catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
		
	}
}
