package com.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 */
public class FrequencySort {

    public static void main(String[] args) {

        System.out.println(frequencySort("tree"));

    }

    private static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for ( int i = 0; i < s.length(); i++ ) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        StringBuilder res = new StringBuilder();
        Map<Character, Integer> tmp = new HashMap<>(map);
        for (int i = 0; i < s.length(); i++) {
            for (char c : tmp.keySet()) {
                tmp.put(c, tmp.get(c) - 1);
                if (tmp.get(c) == 0) {
                    for (int j = 0; j < map.get(c); j ++) {
                        res.append(c);
                    }
                }
            }
        }

        return res.reverse().toString();
    }
}
