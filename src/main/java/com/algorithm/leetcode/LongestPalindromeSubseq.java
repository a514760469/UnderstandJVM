package com.algorithm.leetcode;

import com.algorithm.util.PrintUtils;

/**
 * 516. 最长回文子序列
 * @author zhanglifeng
 * @since 2020-06-01 14:11
 */
public class LongestPalindromeSubseq {

    public static void main(String[] args) {
        LongestPalindromeSubseq solution = new LongestPalindromeSubseq();
        int i = solution.longestPalindromeSubseq("aaba");
        System.out.println(i);
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];// dp[i][j] 表示i到j最长回文子序列 dp[0][n - 1]
        // dp[i][j]  i <= j    base case
        for (int i = 0; i < n; i++ )  {
            dp[i][i] = 1;// i == j
        }

        for (int i = n - 1; i >= 0 ; i--) {
            for (int j = i + 1; j <= n - 1; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                PrintUtils.printDimensionalArrays(dp);
            }
        }

        return dp[0][n - 1];
    }
}
