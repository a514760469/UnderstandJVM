package com.algorithm.recursion;

import java.util.Stack;

/**
 * 递归逆序一个栈
 * @author zhanglifeng
 * @since 2023-08-30
 */
public class ReverseStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        reverse(stack);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int out = bottomOut(stack);
        reverse(stack);
        stack.push(out);
    }

    /**
     * 栈底元素移除掉，上面的元素盖下来，返回移除的元素
     */
    public static int bottomOut(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            int last = bottomOut(stack);
            stack.push(pop);
            return last;
        }
    }

}
