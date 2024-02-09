package com.annotation.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.annotation.TestAnnotation;

@TestAnnotation(id = 100, msg = "abc")
public class Test {
	
//	@TestAnnotation(value = "11111")
	private String field;
	
	public Test() {
		
	}
	
	public void show() {
		
	}
	
	public static void main(String[] args) {
		Class<?> cls = Test.class;
		Annotation[] annotations = cls.getAnnotations();
		boolean present = cls.isAnnotationPresent(TestAnnotation.class);
		System.out.println("isAnnotationPresent: " + present);
		TestAnnotation testAnnotation = cls.getAnnotation(TestAnnotation.class);
		Class<? extends Annotation> at = testAnnotation.annotationType();
		System.out.println("at.getName(): " + at.getName());
		System.out.println("testAnnotation.id(): " + testAnnotation.id());
		System.out.println("testAnnotation.msg(): " + testAnnotation.msg());
		Field[] clasFields = cls.getDeclaredFields();
		for (Field field : clasFields) {
			boolean annotationPresent = field.isAnnotationPresent(TestAnnotation.class);
			if(annotationPresent) {
				TestAnnotation fann = field.getAnnotation(TestAnnotation.class);
				System.out.println("fann.id(): " + fann.id());
				System.out.println("fann.msg(): " + fann.msg());
				System.err.println("fann.value(): " + fann.value());
			}
			
		}
		
		for (Annotation annotation : annotations) {
			Class<? extends Annotation> annotationType = annotation.annotationType();
			System.out.println("isAssignable: " + annotationType.isAssignableFrom(cls));
			String aname = annotationType.getName();
			System.out.println("name: " + aname);
			System.out.println("TypeName: " + annotationType.getTypeName());
			System.out.println("Modifiers: " + annotationType.getModifiers());
			Field[] fields = annotationType.getFields();
			System.out.println("fields.length: " + fields.length);
			for (Field field : fields) {
				System.out.println(field.getName());
			}
		}
	}
}
