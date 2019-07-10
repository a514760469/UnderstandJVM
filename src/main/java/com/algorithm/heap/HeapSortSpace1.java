package com.algorithm.heap;

import com.algorithm.sort.SortTestHelper;

public class HeapSortSpace1 {

    public static void main(String[] args) {
        int n = 1000000;
        int[] array = SortTestHelper.generateRandomArray(n, 0, n);
        SortTestHelper.testSort(HeapSortSpace1.class, "heapSort", array);
    }

    /**
     * 堆排序实现
     * @param arr
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;
        heapSort(arr, n);
    }

    /**
     * 堆排序实现
     * @param arr
     * @param n
     */
    private static void heapSort(int[] arr, int n) {

        // heapify
        for ( int i = (n - 1) / 2; i >= 0; i -- ) {
            shiftDown(arr, n, i);
        }

        for ( int i = n - 1; i > 0; i -- ) {
            SortTestHelper.swap(arr, 0, i);
            shiftDown(arr, i, 0);
        }
    }

    private static void shiftDown(int[] arr, int n, int k) {
        while ( 2 * k + 1 < n ) {
            int j = 2 * k + 1; // j : 左孩子  j + 1 : 右孩子
            if( j + 1 < n && arr[j + 1] > arr[j] ) {  // 判断有右孩子且右孩子大于左孩子
                j += 1;
            }
            if( arr[k] >= arr[j] ) {
                break;
            }
            SortTestHelper.swap(arr, j, k);
            k = j;
        }

    }
}
