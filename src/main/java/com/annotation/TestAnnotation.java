package com.annotation;

import java.lang.annotation.*;

/**
 * 1 @Retention 保留期
 */
// 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE_PARAMETER, ElementType.TYPE, ElementType.METHOD})
public @interface TestAnnotation {
	
	int id() default 1;
	
	String msg() default "a";

	String value() default "";
}
