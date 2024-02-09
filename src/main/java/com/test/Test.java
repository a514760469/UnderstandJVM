package com.test;

import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhanglifeng
 * @since 2021-01-11
 */
public class Test {



    public class A {


    }
    class Inner {
        public String  v1 = "Fake News";
        public String v2 = "Go ahead";
    }

    private static String GetVal() {
        try {
            return Inner.class.newInstance().v1;
        } catch (Exception e) {
            try {
                return Inner.class.getDeclaredConstructor(A.class).newInstance((A)null).v2;
            } catch (Exception ee) {
                ee.printStackTrace();
                return "Fake News, Go ahead";
            }
        }
    }
    public static void main(String[] args) {
        int[][] dp = new int[2][2];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }


        String bin = "10000000000000000000000000000000";
        System.out.println(Integer.parseInt(bin, 2));

        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
    }

    static class StrObject {
        String str;

        public StrObject(String str) {
            this.str = str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }
}
