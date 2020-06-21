package com.algorithm.leetcode;

/**
 * 168. Excel表列名称
 * @author zhanglifeng
 * @since 2020-05-20 10:50
 */
public class ConvertToExcelColumn {

    public static void main(String[] args) {
        String convert = new ConvertToExcelColumn().convertToTitle(28);
        System.out.println(convert);
    }

    /**
     * n
     */
    public String convertToTitle(int n) {
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
