package com.algorithm.hj;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanglifeng
 * @since 2023-08-31
 */
public class MinCoverStr {

    public static void main(String[] args) {
        String res = new MinCoverStr().minWindow("XDOYEZODEYXNZ", "XYZ");
        System.out.println("res = " + res);
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);

//        Map<String, Integer> map = f(formula.toCharArray(), 0);
//        map.keySet()
//        for (String key : map.keys()) {

//        }
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param S string字符串
     * @param T string字符串
     * @return string字符串
     */
    public String minWindow (String S, String T) {
        // write code here
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int l = 0;
        int r = 0;
        String ans = "";
        while(r < S.length()) {
            char current = S.charAt(r);
            Integer value = map.get(current);
            if (value != null) {
                map.put(current, value - 1);
            }

            while (checkMap(map)) {
                String res = S.substring(l, r + 1);
                if ("".equals(ans)) {
                    ans = res;
                } else if (res.length() < ans.length()) {
                    ans = res;
                }
                // 尝试收缩窗口
                if (T.contains(S.charAt(l) + "")) {
                    map.put(S.charAt(l), map.get(S.charAt(l)) + 1);
                }
                l++;
            }
            r++;
        }
        return ans;
    }

    public static boolean checkMap(Map<Character, Integer> map) {
        for (Integer value : map.values()) {
            if (value > 0) {
                return false;
            }
        }
        return true;
    }
}
