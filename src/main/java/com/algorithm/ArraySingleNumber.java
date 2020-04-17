package com.algorithm;

/**
 * 一个非空数组，除了某个元素出现一次，其他元素都会出现两次。
 * @author zhanglifeng
 * @since 2020-04-17 11:03
 */
public class ArraySingleNumber {

    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2};
        System.out.println(singleNumber(arr));
    }

    /**
     * 异或运算，相同为0，不同为1
     *
     * 满足：a ^ b ^ a = b
     *
     * @return
     */
    public static int singleNumber(int[] nums) {
        int complete = 0;
        for (int num : nums) {
            complete = complete ^ num;
        }
        return complete;
    }
}
