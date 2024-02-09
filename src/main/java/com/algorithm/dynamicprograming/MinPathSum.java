package com.algorithm.dynamicprograming;

/**
 * 最小路径和
 * https://leetcode.cn/problems/minimum-path-sum/
 *
 * @author zhanglifeng
 * @since 2023-09-12
 */
public class MinPathSum {

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int ans = new MinPathSum().minPathSum(grid);
        System.out.println("ans = " + ans);
    }



    public int minPathSum(int[][] grid) {
        int r = grid.length; // r 行
        int c = grid[0].length;// c 列

        // dp[行][列]
        int[][] dp = new int[r][c];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < c; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
            System.out.println("dp[0][i] = " + dp[0][i]);
        }

        for (int i = 1; i < r; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
            System.out.println("dp[i][0] = " + dp[i][0]);
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[r - 1][c - 1];
    }
}
