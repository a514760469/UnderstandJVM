package com.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 算法：从排序数组中删除重复项 
 */
public class RemoveDuplicate {
	
	public static void main(String[] args) {
		int[] nums = {1, 1, 1, 2, 2, 3};
		// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
		int len = removeDuplicates(nums);

		// 在函数里修改输入数组对于调用者是可见的。
		// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
		for (int i = 0; i < len; i++) {
		    System.out.println(nums[i]);
		}
	}
	/**
	 * arr	1  1  1  2  2  3
	 * 指针	i  j
	 * @param nums
	 * @return
	 */
	public static int removeDuplicates(int[] nums) {
		int i = 0;
		for (int j = 1; j < nums.length; j++ ) {
            if (nums[i] != nums[j]) {
            	i++;
            	nums[i] = nums[j];
            }
        }
        return i + 1;
    }
	
	/**
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 2; i++) {
            for(int j = i + 1;j < nums.length - 1; j++) {
                for(int k = j + 1; k < nums.length; k++) {
                    if(nums[i] + nums[j] + nums[k] == 0) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return res;
    }
	
}

