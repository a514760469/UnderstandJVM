package com.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 三数之和
 *  nums 中是否存在三个元素 a + b + c = 0 所有满足条件且不重复的三元组
 */
public class ThreeSum {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    /**
     * 无法去掉重复
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++ ) {
            Set<Integer> record = new HashSet<>();
            int target = 0 - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                List<Integer> list = new ArrayList<>();
                int t = target - nums[j];
                if(record.contains(t)) {
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(t);
                } else {
                    record.add(nums[j]);
                }
                if(!list.isEmpty()) {
                    res.add(list);
                }
            }
        }

        return res;
    }
}
