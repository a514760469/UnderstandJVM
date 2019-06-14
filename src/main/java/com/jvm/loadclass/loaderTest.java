package com.jvm.loadclass;

public class loaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = loaderTest.class.getClassLoader();
        System.out.println(classLoader);
        Class<?> aClass = classLoader.loadClass("com.jvm.loadclass.BeenLoaded");
        System.out.println(aClass.hashCode());
//        Class<?> aClass2 = Class.forName("com.jvm.loadclass.BeenLoaded");
//        System.out.println(aClass2.hashCode());
        Class<?> aClass3 = Class.forName("com.jvm.loadclass.BeenLoaded", true, classLoader);
        System.out.println(aClass3.hashCode());
    }

}
