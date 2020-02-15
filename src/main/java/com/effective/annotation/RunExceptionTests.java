package com.effective.annotation;

import java.lang.reflect.Method;

public class RunExceptionTests {

    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName(args[0]);
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExceptionTest.class)
                    || method.isAnnotationPresent(ExceptionTestContainer.class)) {

                tests++;
                try {
                    method.invoke(null);
                    System.out.printf("Test %s Failed: no exception %n ", method);
                } catch (Throwable wrappedExc) {
                    Throwable cause = wrappedExc.getCause();
                    int oldPassed = passed;
                    ExceptionTest[] exceptionTests = method.getAnnotationsByType(ExceptionTest.class);
                    for (ExceptionTest exceptionTest : exceptionTests) {
                        if (exceptionTest.value().isInstance(cause)) {
                            passed++;
                            break;
                        }
                    }

                    if (passed == oldPassed) {
                        System.out.printf("Test %s Failed: %s%n", method, cause);
                    }
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
    }

}
