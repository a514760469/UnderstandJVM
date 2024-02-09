package com.algorithm.hj;

import java.util.Stack;

/**
 * @author zhanglifeng
 * @since 2023-08-24
 */
public class ValidString {

    public static void main(String[] args) {
        boolean b = new ValidString().isValidString("((*)");
        System.out.println("b = " + b);
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param s string字符串
     * @return bool布尔型
     */
    public boolean isValidString (String s) {
        // write code here
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> star = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            }
            else if (c == '*') {
                star.push(i);
            }
            else {
                // 如果出现右括号
                if (!stack.isEmpty()) {
                    stack.pop();
                } else if (!star.isEmpty()) {
                    star.pop();
                } else{
                    return false;
                }
            }
        }

        while (!stack.isEmpty() && !star.isEmpty()) {
            int l = stack.pop();
            int x = star.pop();
            if (x < l) {
                return false;
            }

        }
        // System.out.println(stack);
        // System.out.println(star);
        return stack.isEmpty();
    }


}
