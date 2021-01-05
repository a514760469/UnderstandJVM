package com.algorithm.leetcode;

/**
 * 股票买卖问题
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 *
 * @author zhanglifeng
 * @since 2020-11-17
 */
public class MaxProfit {

    public static void main(String[] args) {
        MaxProfit solution = new MaxProfit();
//        int i = solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        int i = solution.maxProfit_k2(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
        System.out.println(i);
    }

    /**
     * 买一次
     *
     * @param prices prices
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int dpi0 = 0;
        int dpi1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);
            dpi1 = Math.max(dpi1, -prices[i]);
        }
        return dpi0;
    }

    /**
     * k = 2
     * 只能买卖2次
     */
    public int maxProfit_k2(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int dpi10 = 0;
        int dpi11 = Integer.MIN_VALUE;
        int dpi20 = 0;
        int dpi21 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            dpi10 = Math.max(dpi10, dpi11 + prices[i]);
            dpi11 = Math.max(dpi11, -prices[i]);
            dpi20 = Math.max(dpi20, dpi21 + prices[i]);
            dpi21 = Math.max(dpi21, dpi10 - prices[i]);
        }
        return dpi20;
    }

}
