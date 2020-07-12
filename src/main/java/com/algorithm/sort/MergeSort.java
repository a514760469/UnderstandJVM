package com.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    private void mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    /**
     * 将arr[l...mid] 和 arr[mid+1...r] 两部分归并
     */
    private void merge(int[] arr, int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(arr, l, r + 1);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
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


    public void mergeSort(int[] arr, int n) {
        mergeSort(arr, 0, n - 1);
    }
}
