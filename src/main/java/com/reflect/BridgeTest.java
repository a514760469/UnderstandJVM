package com.reflect;

import java.lang.reflect.Method;

/**
 * Method
 * isBridge() 判断一个方法是否是桥接方法
 * 桥接方法: 以免多态破坏在编译时加入的方法
 *
 * @author zhanglifeng
 * @since 2021-06-29
 */
public class BridgeTest<T> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<BridgeTestSub> bridgeTestSubClass = BridgeTestSub.class;
//        Method setT = bridgeTestSubClass.getMethod("setT", Object.class);
        Method setT = bridgeTestSubClass.getMethod("setT", SimpleObject.class);
        System.out.println(setT.isBridge());
    }

    public static class BridgeTestSub extends BridgeTest<SimpleObject> {

        @Override
        public void setT(SimpleObject simpleObject) {
            super.setT(simpleObject);
        }
    }
}
