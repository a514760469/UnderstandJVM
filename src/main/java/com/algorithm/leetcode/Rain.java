package com.algorithm.leetcode;

import java.util.Stack;

/**
 * 接雨水
 */
public class Rain {
	
	public static void main(String[] args) {
		System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
	}
	/**
	 * [ 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 ]
	 * @param a
	 * @return
	 */
	public static int trap(int[] a) {
		Stack<Integer> stack = new Stack<>();
		int res = 0;
		for(int i = 0; i < a.length; i++) {
			if(stack.isEmpty() || a[i] < a[stack.peek()]) {// 比栈顶元素小
				stack.push(i);
			} else {
				while (!stack.empty() && a[stack.peek()] <= a[i]) {
					int peek = stack.pop();// 栈顶元素
					if (!stack.empty()) {
						res = res + (i - stack.peek() - 1) * (Math.min(a[i], a[stack.peek()]) - a[peek]);
					}
				}
				stack.push(i);
			}
		}
		return res;
	}
	
}
