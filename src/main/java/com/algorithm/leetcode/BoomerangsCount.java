package com.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 447. 回旋镖的数量
 */
public class BoomerangsCount {

    public static void main(String[] args) {
        System.out.println(numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}}));
    }

    /**
     * 找一个点i
     * point i  dis: 10     1   0
     *          dis:  5     1   0
     *          dis:  2     2   2*1
     *          dis:  1     3   3*2
     * @param points 点数组   [[1,0],[0,0],[2,0]]
     * @return 回旋镖的数量
     */
    public static int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> record = new HashMap<>();
            for (int j = 0; j < points.length; j++ ) {
                record.put(dis(points[i], points[j]),
                        record.getOrDefault(dis(points[i], points[j]), 0) + 1);
            }
            for (Integer key : record.keySet()) {
                res += record.get(key) * (record.get(key) - 1);
            }
        }
        return res;
    }

    /**
     * 计算两点之间的距离，避免产生浮点数，不进行开根号
     * @param p1
     * @param p2
     * @return
     */
    private static int dis(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

}
