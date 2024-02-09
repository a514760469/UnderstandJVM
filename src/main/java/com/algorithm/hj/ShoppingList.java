package com.algorithm.hj;

import java.util.Scanner;

/**
 * @author zhanglifeng
 * @since 2023-08-18
 */
public class ShoppingList {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int a = in.nextInt();
            System.out.println("a = " + a);
            String b = in.nextLine();
            System.out.println("b = " + b);
        }

//        String arg = "10.0.3.193";
//        ipToInt("183.31.75.22");
//        intToIp("3868643487");
    }


    /**
     * IP 字符串 转num
     * @param ip ip
     */
    public static void ipToInt(String ip) {
        String[] split = ip.split("\\.");
        long ans = 0;
        for (int i = 3; i >= 0; i--) {
            long a = Long.parseLong(split[i]) << (8 * (3 - i));
//            int a = Integer.parseInt(split[i]) << (8 * (3 - i));
            ans |= a;
        }
        System.out.println(ans);
    }

    public static void intToIp(String num) {
        long number = Long.parseLong(num);
//        int number = Integer.parseInt(num);
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.insert(0, number & 255).insert(0, ".");
            number = number >> 8;
        }
        System.out.println(sb.substring(1, sb.length()));
    }
}
