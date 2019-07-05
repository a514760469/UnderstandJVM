package com.algorithm.leetcode;

import java.util.Arrays;
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
        System.out.println(map);

        map.keySet();

        return null;
    }
}
