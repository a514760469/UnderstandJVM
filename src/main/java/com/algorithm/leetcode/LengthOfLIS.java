package com.algorithm.leetcode;

import java.util.Arrays;

/**
 * 300. 最长上升子序列
 *
 * @author zhanglifeng
 * @since 2020-05-22 14:25
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int res = new LengthOfLIS().lengthOfLIS(nums);
        System.out.println(res);
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int value : dp) {
            max = Math.max(max, value);
        }
        return max;
    }
}
