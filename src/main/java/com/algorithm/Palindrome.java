package com.algorithm;

/**
 * 验证回文  忽略除字母数字外的其他字符，忽略大小写
 */
public class Palindrome {

    public static void main(String[] args) {

        System.out.println(isPalindrome(".,"));

    }

    public static boolean isPalindrome(String s) {
        if (s.length() == 0)
            return true;
        int i = 0;
        int j = s.length() - 1;
        char l;
        char r;
        while (i < j) {
            l = s.charAt(i);
            while (!((l >= '0' && l <= '9') || (l >= 'a' && l <= 'z') || (l >= 'A' && l <= 'Z'))) {
                if (++i < s.length()) {
                    l = s.charAt(i);
                } else {
                    return true;
                }
            }
            r = s.charAt(j);
            while (!((r >= '0' && r <= '9') || (r >= 'a' && r <= 'z') || (r >= 'A' && r <= 'Z'))) {
                if (--j >= 0) {
                    r = s.charAt(j);
                } else {
                    return true;
                }
            }
            if ((r + "").equalsIgnoreCase(l + "")) {
                i++;
                j--;
            } else {
                return false;
            }
        }

        return true;
    }

}
