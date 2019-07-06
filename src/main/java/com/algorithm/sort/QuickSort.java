package com.algorithm.sort;

import org.junit.Test;

import static com.algorithm.sort.SortTestHelper.*;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

    }

    /**
     * 快速排序测试
     */
    @Test
    public void testQuickSort() {
        int n = 1000000;
        int[] arr = generateRandomArray(n, 0, 10);
        printArray(arr);
        testSort(getClass(), "quickSort", arr);
        printArray(arr);
    }
    /**
     * 快速排序
     * @param arr
     */
    public static void quickSort(int[] arr) {
        int n = arr.length;
        quickSort(arr, 0, n - 1);
    }

    /**
     * 递归快排
     * @param arr
     * @param l
     * @param r
     */
    private static void quickSort(int[] arr, int l, int r) {

        if (l >= r) return;
//        int p = partition(arr, l, r);
        // 优化：
        int p = partition2(arr, l, r);
        // 递归进行左右两侧排序
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    /**
     * 对arr进行 partition 操作
     * @param arr
     * @param l
     * @param r
     * @return 返回p 使得arr[l..p-1] < arr[p]  arr[p+1...r] > arr[p]
     */
    private static int partition(int[] arr, int l, int r) {
        swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
        int v = arr[l];
        // arr[l+1..j] < v   arr[j+1 ..i) > v
        int j = l;// 初始情况下[l+1..j] 不存在
        for ( int i = l + 1; i <= r; i++ ) {
            // arr[i] < v
            if (arr[i] < v) {
                swap(arr, j + 1, i);
                j++;
            }
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 优化快排 双路快排法
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int partition2(int[] arr, int l, int r) {
        swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
        int v = arr[l];
        // arr[l+1..i] <= v   arr[j+1 ..r] >= v
        int i = l + 1;
        int j = r;
        while (true) {
            while ( i <= r && arr[i] < v) i++;
            while ( j >= l + 1 && arr[j] > v) j--;
            if ( i > j ) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

}
