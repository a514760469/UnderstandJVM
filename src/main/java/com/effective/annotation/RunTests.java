package com.effective.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {

    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName(args[0]);
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    method.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable cause = wrappedExc.getCause();
                    System.out.println(method + " failed: " + cause);
                } catch (Exception e) {
                    System.out.println("Invalid @Test: " + method);
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
    }

}
