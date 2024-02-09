package com.reflect;

import com.annotation.TestAnnotation;
import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * 定义 3 个类型变量
 *
 * @author zhanglifeng
 * @since 2021-06-23
 */
public class TypeVariableTest<T, E extends SimpleObject, F extends E> {


    /**
     * getBounds() 返回上边界类型 如果没有上边界返回 Object 类型
     */
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        TypeVariable<Class<TypeVariableTest>>[] typeParameters = TypeVariableTest.class.getTypeParameters();
        for (TypeVariable<Class<TypeVariableTest>> typeParameter : typeParameters) {
            System.out.println(Arrays.toString(typeParameter.getBounds()));
        }
    }

    /**
     * GenericDeclaration 类型变量声明的地方： 类，接口，Method，Constructor
     */
    @Test
    @SuppressWarnings("rawtypes")
    public void getGenericDeclaration() throws NoSuchMethodException {
        TypeVariable<Class<TypeVariableTest>>[] typeParameters = TypeVariableTest.class.getTypeParameters();
        for (TypeVariable<Class<TypeVariableTest>> typeParameter : typeParameters) {
            Class<TypeVariableTest> genericDeclaration = typeParameter.getGenericDeclaration();
            System.out.println(genericDeclaration);
        }


        TypeVariableTest<String, SimpleObject, SimpleObject> typeVariableTest = new TypeVariableTest<>();

        Method test = typeVariableTest.getClass().getDeclaredMethod("run", SimpleObject.class);
        TypeVariable<Method>[] methodTypeParameters = test.getTypeParameters();
        for (TypeVariable<Method> methodTypeParameter : methodTypeParameters) {
            System.out.println(methodTypeParameter.getGenericDeclaration());
        }
    }



    @Test
    @SuppressWarnings("rawtypes")
    public void getNameTest() {
        TypeVariable<Class<TypeVariableTest>>[] typeParameters = TypeVariableTest.class.getTypeParameters();
        for (TypeVariable<Class<TypeVariableTest>> typeParameter : typeParameters) {
            System.out.println(typeParameter.getName());
        }
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void getAnnotatedBoundsTest() {
        TypeVariable<Class<TypeVariableTest>>[] typeParameters = TypeVariableTest.class.getTypeParameters();
        for (TypeVariable<Class<TypeVariableTest>> typeParameter : typeParameters) {
            AnnotatedType[] annotatedBounds = typeParameter.getAnnotatedBounds();
            System.out.println(Arrays.toString(annotatedBounds));
        }
    }

    public <@TestAnnotation T> void run(SimpleObject simpleObject) {}

}
