package com.algorithm.leetcode;

/**
 * 53. 最大子序和
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * @author zhanglifeng
 * @since 2020-05-28 13:49
 */
public class MaxSubArray {

    public static void main(String[] args) {
        MaxSubArray solution = new MaxSubArray();
        int i = solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(i);
    }

    /**
     *
     */
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                // 相当于清0，重新累加
                sum = nums[i];
            }
            res = Math.max(res, sum);
        }
        return res;
    }

}
