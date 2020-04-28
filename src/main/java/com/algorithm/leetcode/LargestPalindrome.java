package com.algorithm.leetcode;

/**
 * @author zhanglifeng
 * @since 2020-04-23 15:02
 */
public class LargestPalindrome {

    public static void main(String[] args) {
        System.out.println(new LargestPalindrome().largestPalindrome(2));
    }

    public int largestPalindrome(int n) {
        int max = (int) Math.pow(10, n) - 1;
        int lower = max / 10;
        for (long i = max; i > lower; i--) {
            String s = String.valueOf(i);
            long rev = Long.parseLong(s + new StringBuilder(s).reverse().toString());
            // 检验该回文数能否由给定的数相乘得到
            for (long x = max; x * x >= rev; x-- ){
                if (rev % x == 0)
                    return (int) (rev % 1337);
            }
        }
        return 9;
    }
}
