package com.jvm.lock;

/**
 * @author zhanglifeng
 * @since 2020-09-29 9:43
 */
public class Main {

    public static void main(String[] args) {
        testStaticAndClass();
    }


    public static void testThisAndInstance() {
        Synchronized syn = new Synchronized();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    syn.thisMethod();
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    syn.instanceMethod();
                }
            }.start();
        }
    }


    public static void testStaticAndClass() {
        for (int i = 0; i < 10; i++) {
            Synchronized syn = new Synchronized();
            new Thread() {
                @Override
                public void run() {
                    Synchronized.staticMethod();
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    syn.classMethod();
                }
            }.start();
        }
    }
}
