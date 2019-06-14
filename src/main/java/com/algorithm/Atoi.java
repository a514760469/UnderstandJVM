package com.algorithm;

/**
 * 自定义Atoi函数，字符串转整数
 * @author zhanglifeng
 */
public class Atoi {

	public static void main(String[] args) {
//		System.out.println(Integer.parseInt("2147483647"));
		System.out.println(myAtoi("-2147483647"));
	}
	/**
	 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止
	 * @param str
	 * @return
	 */
	public static int myAtoi(String str) {
		int res = 0; 
		boolean isLoseNum = false; // 是否带负号
		// 1 去空格
		int st = 0;
        while ((st < str.length()) && (str.charAt(st) == ' ')) {
            st++;
        }
		str = str.substring(st);
		if(null == str || str.length() == 0) {
			return 0;
		}
		// 2  第一位单独判断
		char firstChar = str.charAt(0);
		if(firstChar >= '0' && firstChar <= '9') {
			res = firstChar - '0';
		} else if (firstChar == '-') {
			isLoseNum = true;
		} else if(firstChar == '+') {
		} else {
			return 0;
		}
		// 3 循环所有连续的数字
		for (int i = 1; i < str.length(); i++ ) {
			char c = str.charAt(i);
			if(c < '0' || c > '9') {
				break;
			}
			if (!isLoseNum && (res > Integer.MAX_VALUE/10 || res == Integer.MAX_VALUE/10 && c - '0' > 7) ) {
				return Integer.MAX_VALUE;
			}
			if (isLoseNum && (-res < Integer.MIN_VALUE/10 || -res == Integer.MIN_VALUE/10 && c - '0' > 8)) {
				return Integer.MIN_VALUE;
			}
			res = res * 10 + c - '0';
		}
		return isLoseNum ? -res : res;
	}
	
	
}


