package com.algorithm.heap;

import com.algorithm.sort.SortTestHelper;

/**
 * 堆排序，利用自己实现的堆排序
 */
public class HeapSortOne {

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] array = SortTestHelper.generateIntegerRandomArray(n, 0, n);
        int[] array2 = SortTestHelper.generateRandomArray(n, 0, n);
//        SortTestHelper.printArray(array);
        SortTestHelper.testSort(HeapSortOne.class, "heapSort1", array2);
        SortTestHelper.testSort(HeapSortOne.class, "heapSort2", array);
//        heapSort2(array);
//        SortTestHelper.printArray(array);
    }

    /**
     * 堆排序实现
     * @param arr
     */
    public static void heapSort1(int[] arr) {
        int n = arr.length;
        heapSort1(arr, n);
    }

    /**
     * 堆排序实现
     * @param arr
     * @param n
     */
    private static void heapSort1(int[] arr, int n) {
        MaxHeap<Comparable> maxHeap = new MaxHeap<>(n);
        for (int i = 0; i < n; i++ ) {
            maxHeap.insert(arr[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            arr[i] = (int) maxHeap.extractMax();
        }
    }

    /**
     * 堆排序实现2
     * @param arr
     */
    public static void heapSort2(Comparable[] arr) {
        int n = arr.length;
        heapSort2(arr, n);
    }
    /**
     * 堆排序实现2
     * @param arr
     * @param n
     */
    private static void heapSort2(Comparable[] arr, int n) {
        MaxHeap<Comparable> maxHeap = new MaxHeap<>(arr, n);
        for (int i = n - 1; i >= 0; i--) {
            arr[i] = (int) maxHeap.extractMax();
        }
    }


}
