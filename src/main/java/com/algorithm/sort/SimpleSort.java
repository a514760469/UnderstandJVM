package com.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

import static com.algorithm.sort.SortTestHelper.*;

public class SimpleSort {

    public static void main(String[] args) {
//        int[] arr = generateRandomArray(10, 0, 10);
        int[] arr = {10,9,8,7,6,5,4,3,2,1};
        System.out.println(isSorted(arr));
//        selectionSort(arr, 10);
        insertionSort(arr, 10);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(isSorted(arr));
    }
    /*
     * O(n^2) 级别的排序算法
     */

    /**
     * 选择排序
     */
    static void selectionSort(int[] arr, int n) {

        for (int i = 0; i < n; i++ ) {

            // 寻找[i, n) 最小值
            int minIndex = i; // 最小值索引 默认i位置
            for (int j = i + 1; j < n; j ++ ) {

                if (arr[j] < arr[minIndex]) { // if 成立 minIndex 指针移动
                    minIndex = j;
                }
            }
            // 交换位置 arr[i] arr[minIndex]
            int t = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = t;
        }
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        insertionSort(arr, arr.length);
    }
    /**
     * 插入排序
     * 可以提前终止内层循环，对于近乎有序的数组排序极快
     * @param arr
     * @param n
     */
    static void insertionSort(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            // 寻找元素合适的插入位置
            int e = arr[i];
            int j; // j 保存元素e应该插入的位置
            for ( j = i; j > 0 && arr[j - 1] > e; j -- ) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    /**
     * [l..r] 区间进行插入排序
     * @param arr
     * @param l
     * @param r
     */
    static void insertionSort(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            // 寻找元素合适的插入位置
            int e = arr[i];
            int j; // j 保存元素e应该插入的位置
            for ( j = i; j > l && arr[j - 1] > e; j -- ) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    /*
     * O(nlogn) 级别的排序算法
     */

    @Test
    public void testMergeSort() {
        int n = 100000;
        int[] ints = generateRandomArray(n, 0, n);
        int[] intsCopy = Arrays.copyOf(ints, ints.length);
        printArray(ints);
        testSort(getClass(), "mergeSort", ints);
        printArray(ints);
        printArray(intsCopy);
        testSort(getClass(), "insertionSort", intsCopy);
        printArray(intsCopy);
    }
    /**
     * 归并排序
     * @param arr
     */
    public void mergeSort(int[] arr) {
        int n = arr.length;
        // 左闭右闭区间
        mergeSort(arr, 0, n - 1);
    }

    /**
     * 归并排序递归方法
     * @param arr
     * @param l
     * @param r
     */
    private static void mergeSort(int[] arr, int l, int r) {
//        if (l >= r)
//            return;
        // 优化： 如果数组数量较少使用插入排序
        if (r - l <= 15) {
            insertionSort(arr, l, r);
            return;
        }

        int mid = (l + r)/2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        // 优化： 当左边最大比右边第一个小时，不执行排序 对近乎有序的数组性能提升
        if(arr[mid] > arr[mid + 1])
            merge(arr, l, mid, r);
    }

    /**
     * [l..mid] 和 [mid + 1...r] 两部分进行归并
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private static void merge(int[] arr, int l, int mid, int r) {
        // 辅助空间   [l...r] 注意：Arrays.copyOfRange 是左闭右开区间
        int[] aux = Arrays.copyOfRange(arr, l, r + 1);
        // 使用aux进行判断, 最后的结果放入arr中
        int i = l; // i 左边索引
        int j = mid + 1;// j 右边索引
        // 索引k arr中要放入元素的位置   从 l 到 r
        for (int k = l; k <= r; k ++ ) {
            // 确认索引的合法性，避免左或者右全部元素已经排完的情况
            if(i > mid) {
                // 左边元素全部用完. 只能用右边的了
                arr[k] = aux[j - l];
                j++;
            } else if(j > r) {
                // 右边元素全部用完. 只能用左边的了
                arr[k] = aux[i - l];
                i++;
            }
            else if(aux[i - l] < (aux[j - l])) {
                // 左边 < 右边
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    /**
     * 自底向上的归并排序
     * @param arr
     * @param n
     */
    void mergeSortBottomUp(int[] arr, int n) {


    }

}
