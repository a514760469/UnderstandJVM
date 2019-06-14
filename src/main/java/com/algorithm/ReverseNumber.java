package com.algorithm;

/**
 * 整数反转
 * 
 * @author zhanglifeng
 */
public class ReverseNumber {
	
	public static void main(String[] args) {
		System.out.println(reverse(-120));
	}
	
	
	public static int reverse(int x) {
		int res = 0;
		while(x != 0) {
			int pop = x % 10;
			// 超范围判断： res在没有左移一位时已经大于Integer.MAX_VALUE右移一位   或 最后一位大于7 
			if(res > (Integer.MAX_VALUE/10) || res == Integer.MAX_VALUE/10 && pop > 7) return 0;
			if(res < (Integer.MIN_VALUE/10) || res == Integer.MIN_VALUE/10 && pop < -8) return 0;
			res = res*10 + pop;
			x /= 10;
		}
		return res;
	}
	
}





