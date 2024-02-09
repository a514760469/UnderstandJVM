package com.algorithm.hj;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglifeng
 * @since 2023-08-30
 */
public class Permutations {

    public static void main(String[] args) {
        List<List<Integer>> res = new Permutations().permute(new int[]{1, 2, 3});
        System.out.println("res = " + res);
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        f(nums, 0, ans);
        return ans;
    }


    public static void f(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            ans.add(list);
        } else {
            for (int j = index; j < nums.length; j++) {
                swap(nums, index, j);
                f(nums, index + 1, ans);
                swap(nums, index, j);
            }
        }
    }


    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
