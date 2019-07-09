package com.algorithm.leetcode;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.err.println(groupAnagrams(strs));
    }

    /**
     * ["eat", "tea", "tan", "ate", "nat", "bat"]
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();// 查找表
        for (String str : strs) {
            char[] strChars = str.toCharArray();
            Arrays.sort(strChars);
            if (!map.containsKey(String.valueOf(strChars))) {
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(String.valueOf(strChars), list);
            } else {
                map.get(String.valueOf(strChars)).add(str);
            }
        }
        return new ArrayList<>(map.values());
    }


}
