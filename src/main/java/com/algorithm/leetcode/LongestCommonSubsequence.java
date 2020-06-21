package com.algorithm.leetcode;

import com.algorithm.util.PrintUtils;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 * @author zhanglifeng
 * @since 2020-05-28 10:52
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        LongestCommonSubsequence solution = new LongestCommonSubsequence();
        int i = solution.longestCommonSubsequence("babcde", "acze");
        System.out.println(i);
    }


    /**
     * text1和text2 最长公共子序列
     */
    public int longestCommonSubsequence(String text1, String text2) {
        // base case
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i <= text1.length(); i++ ) {
            for (int j = 1; j <= text2.length(); j++ ) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 找到lcs中的一个字符
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                PrintUtils.printDimensionalArrays(dp);
            }
        }

        return dp[text1.length()][text2.length()];
    }


    /**
     * 暴力递归解法
     */
    char[] text1Chars;
    char[] text2Chars;

    public int longestCommonSubsequence2(String text1, String text2) {
        this.text1Chars = text1.toCharArray();
        this.text2Chars = text2.toCharArray();
        return dp(text1.length() - 1, text2.length() - 1);
    }

    public int dp(int i, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (text1Chars[i] == text2Chars[j]) {
            return dp(i - 1, j - 1) + 1;
        } else {
            return Math.max(dp(i - 1, j), dp(i, j - 1));
        }
    }
}
