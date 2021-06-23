package com.annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @author zhanglifeng
 * @since 2021-06-22
 */
@IndirectlyPresent(@IndirectlyPresentValue(name = "indirectly present value"))
@DirectlyPresent(id = 1, description = "this is a DirectlyPresent")
public class PresentElementTest extends ParentPresentElement {

    /**
     * 四个限定词：  directly present、indirectly present、present 和 associated
     *
     * present:  1、 注解A directly present 在元素 E 上
     *           2、 注解A 没有directly present 在元素 E 上, A present 在 E 的父类上 (E 是一个类)
     *
     *
     *
     * getAnnotations()          present
     * getDeclaredAnnotations()  仅包含 directly present
     * getAnnotationsByType()    可以拿到 @IndirectlyPresentValue 这种注解 ( indirectly )
     *
     *
     * @param args args
     */
    public static void main(String[] args) {

        Class<PresentElementTest> directlyPresentElementClass = PresentElementTest.class;
//        Annotation[] annotations = directlyPresentElementClass.getAnnotations();
//        Annotation[] annotations = directlyPresentElementClass.getAnnotationsByType(IndirectlyPresentValue.class);
        Annotation[] annotations = directlyPresentElementClass.getDeclaredAnnotations();
        Arrays.stream(annotations).forEach(System.out::println);
//        DirectlyPresent annotation = directlyPresentElementClass.getDeclaredAnnotation(DirectlyPresent.class);
//        System.out.println(annotation.description());
//        System.out.println(annotation.id());
    }
}
