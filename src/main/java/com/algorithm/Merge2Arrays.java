package com.algorithm;

/**
 * 合并两个有序数组
 */
public class Merge2Arrays {

    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums1 = {2, 0};
        int[] nums2 = {1}; // {2, 5, 6}
        merge(nums1, 1, nums2, 1);
        for (int i : nums1) {
            System.out.println(i);
        }
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0; // nums1[i...m-1]  // nums1[i...m-1)
        int j = 0; // nums2[j...n-1]  // nums2[j...n-1)
        while (j < n) {
            if (nums1[i] < nums2[j] && i <= m - 1) {
                i++;
            } else { //(nums1[i] >= nums2[j])
                // 从i开始nums1后移一位    k： nums1末尾指针 [i...m-1]
                for (int k = m - 1; k >= i; k--) {
                    nums1[k + 1] = nums1[k];
                }
                m++;
                nums1[i] = nums2[j];
                j++;
            }
        }

    }
}