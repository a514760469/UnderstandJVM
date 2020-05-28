package com.algorithm.util;

/**
 * @author zhanglifeng
 * @since 2020-05-28 11:24
 */
public class PrintUtils {
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
