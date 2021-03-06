package com.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数是不是“快乐数”。
 *
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 *
 * 示例:
 *
 * 输入: 19
 * 输出: true
 * 解释:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class IsHappy {

    public static void main(String[] args) {
        System.out.println(isHappy(1));
    }

    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int happy = n;
        while(true) {
            happy = happy(happy);
            if(set.contains(happy)) {
                return false;
            } else if(happy == 1) {
                return true;
            } else {
                set.add(happy);
            }
        }

    }

    static int happy(int n) {
        int res = 0;
        while (n > 0) {
            int r = n % 10;
            n /= 10;
            res += r * r;
        }
        return res;
    }
}
