package com.test;

import java.util.*;

public class Borly extends Thread {

    public static void main(String[] args) {

        int result = process('A', 'B', 'C', 3);
        System.out.println("result = " + result);
    }

    public static int process(char a, char b, char c, int n) {
        if (n == 1) {
            System.out.println(n + " 从" + a + "顶移动到" + c + "顶");
            return 1;
        }
        int pre = process(a, c, b, n - 1);

        System.out.println(n + " 从" + a + "顶移动到" + c + "顶");

        int nex = process(b, a, c, n - 1);

        return 1 + pre + nex;
    }
}
