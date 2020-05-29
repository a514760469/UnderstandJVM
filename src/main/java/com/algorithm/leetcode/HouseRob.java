package com.algorithm.leetcode;

import java.util.Arrays;

/**
 * 198. 打家劫舍
 * @author zhanglifeng
 * @since 2020-05-29 18:04
 */
public class HouseRob {


    public static void main(String[] args) {
        HouseRob solution = new HouseRob();
        int rob = solution.rob2(new int[]{1, 2, 3, 4, 5});
        System.out.println(rob);
    }

    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(nums, 0, memo);
    }

    /**
     * @param start 从start开始能抢到的最大值
     */
    public int dp(int[] nums, int start, int[] memo) {
        // base case 没有可抢
        if (start >= nums.length) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        // 抢    不抢
        int res = Math.max(nums[start] + dp(nums, start + 2, memo), dp(nums, start + 1, memo));
        memo[start] = res;
        return memo[start];
    }



    public int rob2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 2];// base case: dp[n] = 0
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(nums[i] + dp[i + 2], dp[i + 1]);
        }
        return dp[0];
    }

}
