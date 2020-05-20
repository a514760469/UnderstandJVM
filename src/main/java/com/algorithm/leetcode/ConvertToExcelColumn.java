package com.algorithm.leetcode;

/**
 * 168. Excel表列名称
 * @author zhanglifeng
 * @since 2020-05-20 10:50
 */
public class ConvertToExcelColumn {

    public static void main(String[] args) {
//        char cc = (char) ('A' + 1 - 1);
//        System.out.println(cc);
        String convert = new ConvertToExcelColumn().convertToTitle(28);
        System.out.println(convert);
    }

    /**
     * n
     * 26 / 26 = 1
     * 26 % 26 = 0
     *
     * 28 / 26 = 1;
     * 28 % 26 = 2;
     *
     * 701 / 26 = 26
     * 701 % 26 = 25
     */
    public String convertToTitle(int n) {
//        int c = n / 26;
//        int r = n % 26;
        StringBuilder sb = new StringBuilder();

        while (n != 0) {
            n -= 1;
            char cc = (char) ('A' + (n % 26));
            sb.insert(0, cc);
            n /= 26;
        }
        return sb.toString();
    }
}
