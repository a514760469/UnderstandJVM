package com.algorithm.leetcode;

import java.util.*;

/**
 * 两个数组的交集II
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2, 2]
 */
public class TwoArrayIntersectionII {

    public static void main(String[] args) {
        for (int i : intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2})) {
            System.out.println(i);
        }

    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nums1Map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if( !nums1Map.containsKey(nums1[i]) || nums1Map.get(nums1[i]) == null) {
                nums1Map.put(nums1[i], 1);
            } else {
                nums1Map.put(nums1[i],  nums1Map.get(nums1[i]) + 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if( nums1Map.containsKey(nums2[i]) && nums1Map.get(nums2[i]) != 0) {
                result.add(nums2[i]);
                nums1Map.put(nums2[i], nums1Map.get(nums2[i]) - 1);
            }
        }
        int[] a = new int[result.size()];
        int i = 0;
        for (Integer integer : result) {
            a[i++] = integer;
        }
        return a;
    }

}
