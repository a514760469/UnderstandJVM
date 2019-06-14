package com.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 存在重复元素
 */
public class HaveRepeat {
    /**
     * [1,2,3,1,2,3]
     * 2
     * @param args
     */
    public static void main(String[] args) {
//        System.err.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2));
    }


    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> record = new HashSet<>();
        for (int i = 0; i < nums.length; i++ ) {
            if(record.contains(nums[i])) {
                return true;
            }
            record.add(nums[i]);
            if(record.size() == k + 1) {
                record.remove(nums[i - k]);// 把第一个元素删除
            }
        }
        return false;
    }

    /**
     * TreeSet.ceiling()返回这个集合中大于或等于给定元素的最小元素，如果没有这样的元素，则返回null
     * TreeSet.floor()  返回这个集合中小于或等于给定元素的最大元素，如果没有这样的元素，则返回null
     * @param nums
     * @param k  i 和 j 之间的差的绝对值最大为 ķ
     * @param t nums[i] 和 nums[j] 的差的绝对值最大为 t
     * @return
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> record = new TreeSet<>();
        for (int i = 0; i < nums.length; i++ ) {
            if( null != record.ceiling((long)nums[i] - t ) && record.ceiling((long) nums[i] - (long) t).longValue() <= (long) t + (long) nums[i] ) {
                return true;
            }
            record.add((long)nums[i]);
            if(record.size() == k + 1) {
                record.remove((long) nums[i - k]);// 把第一个元素删除
            }
        }
        return false;
    }
}
