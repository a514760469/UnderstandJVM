package com.algorithm;

/**
 * 背包问题
 * @author zhanglifeng
 * @since 2020-05-26 10:41
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        KnapsackProblem solution = new KnapsackProblem();
//        int result = solution.knapsack(4, 3, new int[]{2, 1, 3}, new int[]{4, 2, 3});
//        System.out.println(result);
        boolean b = solution.canPartition2(new int[]{1, 5, 11, 5});
        System.out.println(b);
    }


    /**
     * 给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。
     * 其中第 i 个物品的重量为 wt[i]，价值为 val[i]，现在让你用这个背包装物品，最多能装的价值是多少？
     * @param W 背包最大装载重量
     * @param N 物品数
     * @param wt 物品的重量
     * @param val 物品的价值
     * @return 装的最大价值
     */
    public int knapsack(int W, int N, int[] wt, int[] val) {
        // dp 数组 base case dp[0][..] = 0,  dp[..][0] = 0
        int[][] dp = new int[N + 1][W + 1];

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                int curW = wt[i - 1];
                int curVal = val[i - 1];
                // 每一件物品都有装或不装两种状态
                if (w - curW < 0) {
                    // 这种情况只能不装
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 装入或者不装入，择优
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - curW] + curVal);
                }
                printDimensionalArrays(dp);
            }
        }

        return dp[N][W];
    }


    /**
     * 416. 分割等和子集
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * (子集背包问题)
     * @param nums [1, 5, 11, 5]
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) { // 如果为奇数，肯定不存在
            return false;
        }

        // dp[N][sum/2]
        int N = nums.length;
        int W = sum / 2;
        boolean[][] dp = new boolean[N + 1][W + 1];
        // base case dp[..][0] = true   dp[0][..] = false
        for (int i = 0; i <= N; i++ ) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= W; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i - 1]];
                }
                printDimensionalArrays(dp);
            }
        }

        return dp[N][W];
    }

    /**
     * 416问题 dp数组降维 节省空间
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if ((sum & 1) == 1) return false;
//        int N = nums.length;
        int W = sum / 2;
        boolean[] dp = new boolean[W + 1];
        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            for (int j = W; j >= 0; j--) {
                if (j >= nums[i]) {
                    dp[j] = dp[j] | dp[j - nums[i]];
                }
            }
        }

        return dp[W];
    }


    /**
     * 518. 零钱兑换 II
     * 给定不同面额的硬币和一个总金额, 写出可以凑成总金额的硬币组合数。每一种面额的硬币有无限个.
     */
    public int change(int amount, int[] coins) {
        int N = coins.length;
        int W = amount;
        int[][] dp = new int[N + 1][W + 1];
        // base case dp[..][0] = 1  dp[0][..] = 0
        for (int i = 0; i < N + 1; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= W; j++) {
                if (coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // coins[i - 1] <= j
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }

        return dp[N][W];
    }

    /**
     * 518. 零钱兑换 II
     * 降维
     */
    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;// base case
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i] <= j) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }

    /**
     * 打印二维数组
     */
    public static void printDimensionalArrays(int[][] dimensionalArrays) {
        for (int[] arrays : dimensionalArrays) {
            for (int i : arrays) {
                System.out.print("\t" + i);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printDimensionalArrays(boolean[][] dimensionalArrays) {
        for (boolean[] arrays : dimensionalArrays) {
            for (boolean i : arrays) {
                System.out.print("\t" + (i ? 1 : 0));
            }
            System.out.println();
        }
        System.out.println();
    }
}
