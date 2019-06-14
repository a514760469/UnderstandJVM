package com.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 两个数组的交集
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 */
public class TwoArrayIntersection {

    public static void main(String[] args) {
        for (int i : intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2})) {
            System.out.println(i);
        }
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        Set<Integer> resultSet = new HashSet<>();
        for (int i : nums1) {
            nums1Set.add(i);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (nums1Set.contains(nums2[i])) {
                resultSet.add(nums2[i]);
            }
        }
        int[] resultArr = new int[resultSet.size()];
        int i = 0;
        for (Integer integer : resultSet) {
            resultArr[i++] = integer;
        }
        return resultArr;
    }

}
