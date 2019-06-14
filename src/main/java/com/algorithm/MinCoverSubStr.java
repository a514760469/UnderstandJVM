package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小覆盖子串
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 *
 */
public class MinCoverSubStr {

    public static void main(String[] args) {
//        System.out.println(minWindow2("bbaa", "aba"));
//        System.out.println(minWindow2("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow2("a", "aa"));
    }

    /**
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow2(String s, String t) {
        // 频率 t 中所有字符出现的频率
        int[] freq = new int[128];
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            freq[c] ++;
        }
        // 记录 s 中所有字符出现的频率
        int[] sbArr = new int[128];
        // 记录 t中都有哪些字符
        List<Integer> has = new ArrayList<>();
        for (int i = 0; i < freq.length; i++ ) {
            if(freq[i] > 0) {
                has.add(i);
            }
        }
        // 滑动窗口start
        int l = 0;
        int r = -1;
        String res = ""; // 结果
        while ( l < s.length()) {
            boolean isContains = isContains(freq, sbArr, has);
            // 不包括且可以右移  r 向右滑动
            if(!isContains && r + 1 < s.length()) {
                r += 1;
                sbArr[s.charAt(r)] ++ ;
            } else if(isContains){
                // 如果包括, 尝试 l 向右移
                do {
                    int sbLength = r - l + 1;
                    if( sbLength < res.length() || "".equals(res)) {
                        res = s.substring(l, r + 1);
                    }
                    sbArr[s.charAt(l)] --;
                    l += 1;
                    // l 右移之后判断是否包含
                } while(isContains(freq, sbArr, has));
            } else {
                l += 1; // 每次循环 l 或者 r 必须有一个+1, 避免无限月读
            }
        }
        return res;
    }

    /**
     * 判断 freq 是否包括了 sbArr 中的全部字母
     * 判断 freq 是否包括了 sb 中的全部字母
     * @param freq
     * @param sbArr
     * @param has freq 中有哪些字母
     * @return
     */
    public static boolean isContains(int[] freq, int[] sbArr, List<Integer> has) {
        for (int i = 0; i < has.size(); i++) {
            if(sbArr[has.get(i)] < freq[has.get(i)]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 这个方法超时了
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        int l = 0;
        int r = -1;
        String sb = "";
        String res = s; // 结果
        boolean haveRes = false;
        while ( l < s.length()) {
            boolean contains = isContains(sb, t);
            if(!contains && r + 1 < s.length()) {
//                haveRes = false;
                r += 1;
                sb += String.valueOf(s.charAt(r));
            } else if(contains){
                do {
                    haveRes = true;
                    if(sb.length() < res.length()) {
                        res = sb;
                    }
                    l += 1;
                    sb = sb.substring(1);
                } while(isContains(sb, t));
            } else {
                l += 1; // 每次循环 l 或者 r 必须有一个+1, 避免无限月读
            }
        }
        if(!haveRes) {
            return "";
        }
        return res;
    }


    // str 是否包含t中的所有字符              bba      aba
    private static boolean isContains(String str, String t) {
        if(str.length() < t.length()) {
            return false;
        }
        for ( int i = 0; i < t.length(); i++ ) {
            if(!str.contains(String.valueOf(t.charAt(i)))) {
                return false;
            } else {
                // 移出已使用的字符
                str = str.replaceFirst(String.valueOf(t.charAt(i)), "");
            }
        }
        return true;
    }
}
