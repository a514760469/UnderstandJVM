package com.algorithm.leetcode;

/**
 * 680. 验证回文字符串 Ⅱ
 * 最多删除一个字符判断字符串s是否是回文。
 * @author zhanglifeng
 * @since 2020-05-19 16:49
 */
public class ValidPalindromeII {

    public static void main(String[] args) {
        boolean palindrome = new ValidPalindromeII().validPalindrome("a");
        System.out.println(palindrome);
    }
    /**
     * s : [l, r]
     */
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            char cl = s.charAt(l);
            char cr = s.charAt(r);
            if (cl == cr) {
                l++;
                r--;
            } else {
                // [l + 1, r]
                boolean f1 = true;
                for (int i = l + 1, j = r; i <= j; i++, j--) {
                    char ci = s.charAt(i);
                    char cj = s.charAt(j);
                    if (ci != cj) {
                        f1 = false;
                    }
                }
                if (f1) {
                    return true;
                }
                // [l, r -1]
                boolean f2 = true;
                for (int i = l, j = r - 1; i <= j; i++, j--) {
                    char ci = s.charAt(i);
                    char cj = s.charAt(j);
                    if (ci != cj) {
                        f2 = false;
                    }
                }
                return f2;
            }
        }
        return true;
    }
}
