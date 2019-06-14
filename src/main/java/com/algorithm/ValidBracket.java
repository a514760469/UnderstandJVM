package com.algorithm;

import java.util.HashMap;
import java.util.Stack;

/**
 * 括号验证
 * @author zhanglifeng
 */
public class ValidBracket {
	
	public static void main(String[] args) {
		System.out.println(isValid("]"));
	}
	/**
	 * @param s  ()
	 * @return
	 */
	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		HashMap<Character, Character> hash = new HashMap<>();
		hash.put('[', ']');
		hash.put('{', '}');
		hash.put('(', ')');
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if(hash.keySet().contains(c[i])) {
				stack.push(c[i]);
			} else if(hash.containsValue(c[i])) {
				// 如果这时栈中没有任何左括号，返回false
				if(stack.isEmpty()) {
					return false;
				}
				// 取出栈的top 作为hash的key查询hash表
				if(hash.get(stack.peek()) == c[i]) {
					stack.pop();
				} else {
					return false;
				}
			} 
		}
		return stack.isEmpty();
    }
	
	
}
