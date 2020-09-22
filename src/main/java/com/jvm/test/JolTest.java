package com.jvm.test;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author zhanglifeng
 * @since 2020-09-21 10:45
 */
public class JolTest {

    public static void main(String[] args) {
        System.out.println((12312 >>> 16));
        System.out.println((1 << 16) - 1);
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

}
