package com.algorithm.leetcode;

import java.util.*;

/**
 * 滑动窗口问题
 * 76. 最小覆盖子串
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 *
 */
public class MinWindow {

    public static void main(String[] args) {
//        System.out.println(minWindow2("bbaa", "aba"));
//        System.out.println(minWindow2("ADOBECODEBANC", "ABC"));
//        System.out.println(minWindow3("ADOBECODEBANC", "AA"));

        System.out.println(checkInclusion("ab", "eidboaoo"));
    }

    /**
     * 567. 字符串的排列
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
     *
     * => s1 = "ab" s2 = "eidbaooo"
     * <= true
     */
    @SuppressWarnings("Duplicates")
    public static boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> needs = new HashMap<>();
        for (char c : s1.toCharArray()) {
            needs.merge(c, 1, Integer::sum);
        }
        Map<Character, Integer> windows = new HashMap<>();
        int left = 0; int right = 0;
        int valid = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            windows.put(c, windows.getOrDefault(c, 0) + 1);
            if (needs.containsKey(c)) {
                if (windows.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            right++;

            // 收缩窗口
            while (right - left >= s1.length()) {
                if (valid == needs.size()) {
                    return true;
                }
                // 移除windows 中的一个字符
                char d = s2.charAt(left);
                if (needs.containsKey(d)) {
                    if (windows.get(d).equals(needs.get(d))) {
                        valid--;
                    }
                }
                windows.put(d, windows.get(d) - 1);
                left++;
            }
        }

        return false;
    }

    /**
     * 76. 最小覆盖子串
     */
    @SuppressWarnings("Duplicates")
    public static String minWindow3(String s, String t) {
        Map<Character, Integer> needs = new HashMap<>();
        for (char c : t.toCharArray()) {
            needs.merge(c, 1, Integer::sum);
//            needs.put(c, needs.getOrDefault(c, 1));
        }

        Map<Character, Integer> windows = new HashMap<>();
        int left = 0; int right = 0; // [l, r)
        int valid = 0;// 匹配的字符数，当valid满足时left应该收缩
        int start = 0;// start
        int len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            windows.put(c, windows.getOrDefault(c, 0) + 1);
            if (needs.containsKey(c)) {
                if (windows.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            right++;

            // left收缩
            while (valid == needs.size()) {
                // 更新结果
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // 移除windows 中的一个字符
                char d = s.charAt(left);
                if (needs.containsKey(c)) {
                    if (windows.get(d).equals(needs.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.get(d) - 1);
                }
                left++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }


    /**
     *
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
