package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Z字形字符串转换 (N字形)
 * 
 * 输入：s = LEETCODEISHIRING   numRows = 3
 * 
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 
 * 输出：LCIRETOESIIGEDHN
 */
public class ZStringConvert {
	
	public static void main(String[] args) {
		String convert = convert("AB", 1);
		System.out.println(convert);
	}
	
	public static String convert(String s, int numRows) {
		if(numRows == 1) return s;
        List<StringBuilder> rowList = new ArrayList<>();// 存放Z字形字符串  初始化
        for (int i = 0; i < Math.min(s.length(), numRows); i++ ) {
        	rowList.add(new StringBuilder());
        }
        boolean isLastRow = false;// 是否到底
        int row = 0;// Z字形 的当前行
        // 开始循环
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
        	char c = charArray[i];
        	rowList.get(row).append(c);
        	row = !isLastRow ? (row + 1) : (row - 1);
        	if(row == numRows - 1) {
        		isLastRow = true;
        	}
        	if(row == 0) {
    			isLastRow = false;
    		}
        }
        
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : rowList) {
			res.append(sb);
		}
        return res.toString();
    }
}
