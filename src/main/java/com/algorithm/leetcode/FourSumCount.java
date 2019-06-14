package com.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 四数之和
 */
public class FourSumCount {

    public static void main(String[] args) {
        System.out.println(fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}));
    }

    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> record = new HashMap<>();
        for ( int i = 0; i < C.length; i++ )
            for (int j = 0; j < D.length; j++ )
                record.put(C[i] + D[j], record.getOrDefault(C[i] + D[j], 0) + 1);

        int res = 0;
        for(int i = 0; i < A.length; i++ ) {
            for (int j = 0; j < B.length; j++ ) {
                if(record.get(0 - A[i] - B[j]) != null) {
                    res += record.get(0 - A[i] - B[j]);
                }
            }
        }
        return res;
    }


}
