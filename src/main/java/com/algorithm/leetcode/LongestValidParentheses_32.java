package com.algorithm.leetcode;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author zhanglifeng
 * @since 2023-05-25
 */
public class LongestValidParentheses_32 {

    public static void main(String[] args) throws IOException {

        int i = new LongestValidParentheses_32().longestValidParentheses("()()(())");
        System.out.println("i = " + i);
    }


    public int longestValidParentheses(String s) {
        char[] ch = s.toCharArray();
        // dp[i]: 子串如果必须以i未知结尾，最长有效长度是多少？ 子串如果以左括号结尾，不可能有效为0
        int[] dp = new int[s.length()];
        // dp[0]为0 从1开始
        for (int i = 1; i < ch.length; i++) {
            if (ch[i] == ')') {
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && ch[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            // else ch[i] == ')'
        }
        return Arrays.stream(dp).max().orElse(0);
    }
}
