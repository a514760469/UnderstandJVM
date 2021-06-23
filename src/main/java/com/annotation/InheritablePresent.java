package com.annotation;

import java.lang.annotation.*;

/**
 * @author zhanglifeng
 * @since 2021-06-22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface InheritablePresent {

}
