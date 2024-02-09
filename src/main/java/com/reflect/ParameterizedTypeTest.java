package com.reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author zhanglifeng
 * @since 2021-06-23
 */
public class ParameterizedTypeTest<T, E> {

    /**
     * getGenericSuperclass()   方法会返回当前Class的参数化的父类 (ParameterizedType)
     *
     * getActualTypeArguments() 获取真实 的 参数类型
     *
     */
    public static void main(String[] args) {
        Class<ParameterizedTypeTestSub> parameterizedTypeTestClass = ParameterizedTypeTestSub.class;
        Type genericSuperclass = parameterizedTypeTestClass.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument);
        }
    }

    /**
     * getRawType()  输出的就是
     */
    @Test
    public void getRawTypeTest() {
        Class<ParameterizedTypeTestSub> parameterizedTypeTestClass = ParameterizedTypeTestSub.class;
        Type genericSuperclass = parameterizedTypeTestClass.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type rawType = parameterizedType.getRawType();
        System.out.println(rawType);
//        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
    }


    public Map.Entry<String, Object> entry;

    @Test
    public void getOwnerType() throws NoSuchFieldException {
        Class<ParameterizedTypeTest> parameterizedTypeTestClass = ParameterizedTypeTest.class;
        Field entry = parameterizedTypeTestClass.getDeclaredField("entry");
        ParameterizedType genericType = (ParameterizedType) entry.getGenericType();
        System.out.println(genericType.getOwnerType());
    }


}

class ParameterizedTypeTestSub extends ParameterizedTypeTest<String, SimpleObject> {

}
