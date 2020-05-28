package com.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 * 1 插入一个字符
 * 2 删除一个字符
 * 3 替换一个字符
 * @author zhanglifeng
 * @since 2020-05-28 14:51
 */
public class MinDistance {

    public static void main(String[] args) {
        MinDistance solution = new MinDistance();
        int i = solution.minDistance2("dinitrophenylhydrazine", "benzalphenylhydrazone");
        System.out.println(i);
    }

    /**
     * 暴力递归 + 备忘录
     */
    char[] s1;
    char[] s2;
    public int minDistance(String word1, String word2) {
        this.s1 = word1.toCharArray();
        this.s2 = word2.toCharArray();
        Map<String, Integer> memo = new HashMap<>();
        return dp(word1.length() - 1, word2.length() - 1, memo);
    }

    /**
     * 返回 s1[0..i] 和 s2[..j] 的最小编辑距离
     * @param memo 备忘录
     */
    public int dp(int i, int j, Map<String, Integer> memo) {
        String key = String.valueOf(i) + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (i == -1) {
            memo.put(key, j + 1);
            return memo.get(key);
        }
        if (j == -1) {
            memo.put(key, i + 1);
            return memo.get(key);
        }

        if (s1[i] == s2[j]) {
            int result = dp(i - 1, j - 1, memo);
            memo.put(key, result);
            return memo.get(key);
        } else {
            // 三选一 最小： s1 s2
            // 插入（insert）  j - 1
            // 删除（delete）  i - 1
            // 替换（replace） i - 1, j - 1
            int min = min(dp(i, j - 1, memo) + 1, dp(i - 1, j, memo) + 1, dp(i - 1, j - 1, memo) + 1);
            memo.put(key, min);
            return memo.get(key);
        }
    }

    public int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * 自底向上dp
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case s1 = "rad", s2 = "apple"
        for (int i = 0; i <= m; i++ ) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++ ) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 三选一 最小： s1 s2
                    // 插入（insert）  i, j - 1
                    // 删除（delete）  i - 1, j
                    // 替换（replace） i - 1, j - 1
                    dp[i][j] = min(
                            dp[i][j - 1] + 1,
                            dp[i - 1][j] + 1,
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }

        return dp[m][n];
    }
}
