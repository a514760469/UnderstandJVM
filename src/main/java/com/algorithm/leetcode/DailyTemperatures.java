package com.algorithm.leetcode;

import com.algorithm.sort.SortTestHelper;
import com.algorithm.util.PrintUtils;
import com.sun.javafx.collections.SortHelper;

import java.util.Stack;

/**
 * 739. 每日温度
 * 给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]
 * @author zhanglifeng
 * @since 2020-06-05 16:43
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();
        int[] res = solution.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        SortTestHelper.printArray(res);
    }

    /**
     * temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
     * ans =  [ , 1, 0, 0]
     */
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? 0 : (stack.peek() - i);
            stack.push(i);// push 索引
        }
        return ans;
    }

}
