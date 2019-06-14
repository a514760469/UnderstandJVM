package com.algorithm;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 */
public class MaxSubLengthNoRepeat {
	
	public static void main(String[] args) {
		
		System.out.println(lengthOfLongestSubstring2("abcabcbb"));
	}

	public static int lengthOfLongestSubstring(String s) {
		String sb = "";
		int maxLength = 0;
        for(int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if(sb.contains(c)) {
                sb = sb.substring(sb.indexOf(c) + 1) + c;
            } else {
                sb += c;
            }
            if(sb.length() > maxLength) {
            	maxLength = sb.length();
            }
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring2 (String s) {
        int l = 0;
        int r = -1;
        int res = 0;
        int[] ascii = new int[256];
        while ( l < s.length() ) {
            if(r + 1 < s.length() && ascii[s.charAt(r + 1)] == 0) {
                r++;
                ascii[s.charAt(r)]++;
            } else {
                ascii[s.charAt(l)]--;
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
	
}
