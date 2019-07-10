package com.algorithm.sort;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * 生成算法测试用例
 */
public class SortTestHelper {
    /**
     * 生成n个元素的随机数组。每个元素随机范围 [rangeL, rangeR]
     * @param n
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static int[] generateRandomArray(int n, int rangeL, int rangeR) {
        // rangeL必须小于rangeR
        assert rangeL <= rangeR;
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rangeL + random.nextInt(rangeR - rangeL + 1);

        }
        return arr;
    }

    /**
     * 生成n个元素的随机数组  Integer[] 类型
     * @param n
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static Integer[] generateIntegerRandomArray(int n, int rangeL, int rangeR) {
        // rangeL必须小于rangeR
        assert rangeL <= rangeR;
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rangeL + random.nextInt(rangeR - rangeL + 1);

        }
        return arr;
    }

    /**
     * 数组是否排序
     * @param arr
     * @return
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1])
                return false;
        }
        return true;
    }

    /**
     * 把指定数组的两个指定下标的元素互换
     *
     * @param arr 指定数组
     * @param i   指定下标1
     * @param j   指定下标2
     */
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 把指定数组的两个指定下标的元素互换
     * 适应Comparable[] 类型
     * @param arr 指定数组
     * @param i   指定下标1
     * @param j   指定下标2
     */
    public static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 打印arr数组的全部内容
     */
    public static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 打印arr数组的全部内容
     */
    public static void printArray(Object[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 测试排序算法的正确性和消耗的时间
     * @param sortClass
     * @param methodName
     * @param arr
     */
    public static void testSort(Class<?> sortClass, String methodName, int[] arr ) {
        try {
            // 使用反射的方式获取所调用的方法
            Method method = sortClass.getMethod(methodName, int[].class);

            long startTime = System.currentTimeMillis();
            method.invoke(sortClass.newInstance(), new Object[]{arr});
            long endTime = System.currentTimeMillis();

            System.out.println(sortClass.getSimpleName() + "." +  methodName + ": " + (endTime - startTime) + "ms");

            assert isSorted(arr);
            if(!isSorted(arr)) {
                throw new Exception("排序错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试排序算法的正确性和消耗的时间
     * @param sortClass
     * @param methodName
     * @param arr
     */
    public static void testSort(Class<?> sortClass, String methodName, Comparable[] arr ) {
        try {
            // 使用反射的方式获取所调用的方法
            Method method = sortClass.getMethod(methodName, Comparable[].class);

            long startTime = System.currentTimeMillis();
            method.invoke(sortClass.newInstance(), new Object[]{arr});
            long endTime = System.currentTimeMillis();

            System.out.println(sortClass.getSimpleName() + "." +  methodName + ": " + (endTime - startTime) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i : generateRandomArray(10000, 0, 10000)) {
            System.out.print(i + " ");
        }
    }
}
