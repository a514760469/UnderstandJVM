package com.algorithm.leetcode;

/**
 * 312. 戳气球
 * @author zhanglifeng
 * @since 2020-06-02 11:26
 */
public class MaxCoins {

    public static void main(String[] args) {
        MaxCoins solution = new MaxCoins();
        int maxCoins = solution.maxCoins(new int[]{3, 1, 5, 8});
        System.out.println(maxCoins);
    }


    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 新数组包含 nums[-1]=points[0] nums[n]=points[n+1]
        int[] points = new int[n + 2];
        points[0] = points[n + 1] = 1;
        for (int i = 0; i < n; i++ ) {
            points[i + 1] = nums[i];
        }
        // dp[i][j] = x 表示：戳破开区间(i, j)的所有气球得到的最高分x， dp[0][n + 1]
        int[][] dp = new int[n + 2][n + 2];

        for (int i = n; i >= 0; i--) {
            for (int j = i + 1; j <= n + 1; j++ ) {
                for (int k = i + 1; k < j; k++ ) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + points[k] * points[i] * points[j]);
                }
            }
        }

        return dp[0][n + 1];
    }
}

