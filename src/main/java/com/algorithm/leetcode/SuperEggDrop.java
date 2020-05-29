package com.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 887. 鸡蛋掉落
 * @author zhanglifeng
 * @since 2020-05-28 18:44
 */
public class SuperEggDrop {


    public static void main(String[] args) {

        SuperEggDrop solution = new SuperEggDrop();
        int i = solution.superEggDrop(4, 5000);
        System.out.println(i);
    }


    /**
     * 暴力递归
     * + 备忘录
     */
    public int superEggDrop(int K, int N) {
        Map<String, Integer> memo = new HashMap<>();
        return dp(K, N, memo);
    }

    /**
     * @param K 个鸡蛋
     * @param N 层楼
     * @return 得到楼层F的最少尝试次数
     */
    public int dp(int K, int N, Map<String, Integer> memo) {
        String key = String.valueOf(K) + N;
        if (memo.get(key) != null) {
            return memo.get(key);
        }
        if (K == 1) { // 鸡蛋数量为1，线性扫描所有楼层
            memo.put(key, N);
            return N;
        }
        if (N == 0) {// 当楼层为0，F = 0
            memo.put(key, 0);
            return 0;
        }
        int res = Integer.MAX_VALUE;
//        for (int i = 1; i <= N; i++) {
//            // 鸡蛋没碎：K, N - i  碎: K - 1, i - 1
//            res = Math.min(res, Math.max(dp(K, N - i, memo), dp(K - 1, i - 1, memo)) + 1);
//        }

        // 二分法
        int low = 1;
        int high = N;
        while (low <= high) {
            int mid = (low + high) / 2;
            int noBroken = dp(K, N - mid, memo);
            int broken = dp(K - 1, mid - 1, memo);
            if (noBroken > broken) {
                low = mid + 1;
                res = Math.min(res, noBroken + 1);
            } else {
                high = mid - 1;
                res = Math.min(res, broken + 1);
            }
        }

        memo.put(key, res);
        return res;
    }
}
