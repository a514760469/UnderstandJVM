package com.algorithm.leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 242 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 */
public class IsAnagram {

    public static void main(String[] args) {
        System.out.println(isAnagram("aa", "a"));
    }

    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            if(map.containsKey(c) && map.get(c) != 0) {
                if(map.get(c) - 1 == 0) {
                    map.remove(c);
                } else {
                    map.put(c, map.get(c) - 1);
                }
            } else {
                return false;
            }
        }
        return map.isEmpty();
    }
}
