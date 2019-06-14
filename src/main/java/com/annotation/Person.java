package com.annotation;

import java.lang.annotation.Inherited;

@Inherited
public @interface Person {
	String role() default "";
	
}
