package com.reflect;

/**
 * @author zhanglifeng
 * @since 2021-06-25
 */
public class ClassTest {

    public static void main(String[] args) throws ClassNotFoundException {
        String s = new String("aClassTestString");

        Class<? extends String> aClass = s.getClass();
        System.out.println(aClass);

        Number number = 2;
        Class<? extends Number> aClass1 = number.getClass();
//        aClass1 = Integer.class;
        System.out.println(aClass1);

        Class<Void> voidClass = void.class;
        Class<Integer> integerClass = int.class;
        System.out.println("integerClass = " + integerClass);
        System.out.println("voidClass = " + voidClass);

        Class<?> classForName = Class.forName("com.reflect.ClassTest");

        System.out.println(classForName);
    }
}
