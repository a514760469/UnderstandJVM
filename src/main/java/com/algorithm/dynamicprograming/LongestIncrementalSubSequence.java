package com.algorithm.dynamicprograming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 最长递增子序列
 * @author zhanglifeng
 * @since 2023-09-10
 */
public class LongestIncrementalSubSequence {

    public static void main(String[] args) {
//        int[] nums = {10,9,2,5,3,7,101,18};
//        int[] nums = {2, 5, 2, 4, 3, 0,1,2,3,4,5,6};
        int[] nums = {7, 7, 7, 7, 7, 7, 7};
//        int[] nums = {637, 261, 159, 367, 3, 6, 667, 860, 483, 476, 724, 891, 323, 884, 348, 559, 752, 821, 724, 140,
//                666, 866, 656, 667, 322, 641, 356, 667, 860, 483, 476, 724, 891, 323, 884, 348, 559, 752, 821, 724,
//                140, 212, 68, 313, 641, 356, 667, 860, 483, 476, 667, 753, 81, 32, 88, 348, 559, 633, 574, 724,
//                997, 215, 681, 311, 641, 356, 667, 860, 483, 476, 667, 77, 11, 302, 808, 318, 590, 63, 74, 697,
//                13, 22, 608, 315, 641, 356, 667, 135, 483, 476, 667, 773, 831, 322, 188, 312, 519, 638, 997, 99,
//                666, 866, 656, 667, 322, 641, 356, 667, 860, 483, 476, 724, 891, 323, 884, 58, 59, 72, 1821, 723,
//                724, 891, 323, 884, 58, 59, 72, 1821, 723, 999, 6662, 1231, 81, 32, 88, 348, 123, 9995, 1211};

        long start = System.currentTimeMillis();
        int res = lengthOfLIS1(nums);


        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println("res = " + res);
    }




    /**
     * 1, 5, 2, 4, 3
     *
     */
    public static int lengthOfLIS1(int[] nums) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            len = Math.max(len, l2(nums, i));
        }
        return len;
    }

    /**
     * 返回从 i 开始最长子序列长度
     */
    public static int l(int[] nums, int i) {
        int maxLen = 1;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[i]) {
                maxLen = Math.max(maxLen, l(nums, j) + 1);
            }
        }
        return maxLen;
    }


    static Map<Integer, Integer> memo = new HashMap<>();

    /**
     * 返回从i开始最长子序列长度memo版本
     */
    public static int l2(int[] nums, int i) {
        if (memo.get(i) != null) {
            return memo.get(i);
        }

        int maxLen = 1;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[i]) {
                maxLen = Math.max(maxLen, l2(nums, j) + 1);
            }
        }
        memo.put(i, maxLen);
        return maxLen;
    }

    /**
     * 1, 5, 2, 4, 3
     */
    public static int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.println("nums[i] = " + nums[i] + ", nums[j] = " + nums[j]);
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            System.out.println();
        }

        return Arrays.stream(dp).max().getAsInt();
    }
}
