package com.effective.annotation;

import java.lang.annotation.*;

/**
 * @Repeatable java8新增元注解
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class)
public @interface ExceptionTest {
    Class<? extends Exception> value();
}
