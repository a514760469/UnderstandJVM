package com.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Retention 保留期
 */
// 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotation {
	
	int id() default 1;
	
	String msg() default "a";
}
