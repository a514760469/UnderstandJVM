package com.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身
 * 示例 1:
 *
 * 输入: s = "egg", t = "add"
 * 输出: true
 *
 */
public class IsIsomorphic {

    public static void main(String[] args) {
        System.out.println(isIsomorphic("ab", "aa"));
    }

    /**
     *
     * @param s
     * @param t
     * @return
     */
    private static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> hash = new HashMap<>();
        for ( int i = 0; i < s.length(); i++ ) {
            char sc = s.charAt(i); // k
            char tc = t.charAt(i); // v
            if(hash.containsKey(sc)) {
                if(hash.get(sc) != tc) {
                    return false;
                }
            } else {
                if (hash.containsValue(tc)) {
                    return false;
                }
                hash.put(sc, tc);
            }
        }
        return true;
    }
}
