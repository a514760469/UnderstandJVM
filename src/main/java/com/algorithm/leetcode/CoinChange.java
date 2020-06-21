package com.algorithm.leetcode;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 凑零钱，凑成amount需要的最少硬币
 * coins: 硬币金额
 * @author zhanglifeng
 * @since 2020-05-20 18:05
 */
public class CoinChange {

    public static void main(String[] args) {

        int res = new CoinChange().coinChange2(new int[]{1, 2, 5}, 3);
        System.out.println(res);
    }

    // ---------------------------自底向上----------------------------

    /**
     * 解法二：自底向上，循环
     * 初始化dp table 为：amount+1(表示一个不可能的情况，相当于MAX_VALUE) 最后结果仍然是amount+1 表示凑不出
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // dp 初始化
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] != amount + 1 ? dp[amount] : -1;
    }

    // ---------------------------自顶向下------------------------------

    private int[] coins;

    /**
     * 解法一：自顶向下，递归
     */
    public int coinChange(int[] coins, int amount) {
        this.coins = coins;
        int[] dp = new int[amount + 1];
        return dpCoin(dp, amount);
    }

    /**
     * @param dp 备忘录数组
     * @param n 当前目标金额
     * @return 所需硬币数
     */
    private int dpCoin(int[] dp, int n) {
        // base case
        if (n == 0) return 0;
        if (n < 0) return -1;// 无解

        if (dp[n] != 0) {
            return dp[n];
        }
        System.out.println("计算：" + n);

        int count = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = n - coin;// 求子问题的解
            int subResult = dpCoin(dp, subProblem);
            if (subResult == -1) {
                continue;
            }
            count = Math.min(subResult + 1, count);
        }
        dp[n] = (count != Integer.MAX_VALUE) ? count : -1;// 如果无解返回-1
        return dp[n];
    }
}
