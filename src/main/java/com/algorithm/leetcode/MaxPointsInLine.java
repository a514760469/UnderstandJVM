package com.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. 直线上最多的点数
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上
 */
public class MaxPointsInLine {

    public static void main(String[] args) {
        System.err.println(gcd(8, 6));
    }

    /**
     * [[1,1],[2,2],[3,3]]                          3
     * [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]        4
     *
     * @param points
     * @return
     */
    public static int maxPoints(int[][] points) {
        return 0;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
