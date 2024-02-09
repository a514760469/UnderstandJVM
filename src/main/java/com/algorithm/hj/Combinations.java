package com.algorithm.hj;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhanglifeng
 * @since 2023-08-30
 */
public class Combinations {

    public static void main(String[] args) {
        List<List<Integer>> ans = new Combinations().subsetsWithDup(new int[]{1, 2, 2});
        System.out.println(ans);
    }


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        f(nums, 0, new int[nums.length], 0, ans);
        return ans;
    }


    public static void f(int[] nums, int i, int[] path, int size, List<List<Integer>> ans) {
        if (i == nums.length) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                list.add(path[j]);
            }
            ans.add(list);
        } else {
            int j = i + 1;
            while (j < nums.length && nums[i] == nums[j]) {
                j++;
            }

            // 不添加当前位置的数 直接调到j位置添加下一个数
            f(nums, j, path, size, ans);
            // 添加当前位置的数
            for (; i < j; i++) {
                path[size] = nums[i];
                size += 1;
                f(nums, j, path, size, ans);
            }

        }
    }
}
