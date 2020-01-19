package com.test;

import java.util.*;

public class CompareListMap {
    static Map<String, String> map1 = new HashMap<>();
    static Map<String, String> map2 = new HashMap<>();

    static {
        map1.put("a", "1");
        map1.put("b", "21");
        map1.put("c", "1");
        map1.put("d", "1");

        map2.put("a", "1");
        map2.put("b", "21");
        map2.put("c", "12");
        map2.put("asd", "1");
        map2.put("d", "123");
    }

    public static void main(String[] args) {
        // 待去重List
        List<Map<String, String>> mapList = new ArrayList<>();

        List<Map<String, String>> noRepeatList = new ArrayList<>();


        mapList.forEach(strStrMap -> {
            // 如果不包含，则放入noRepeat中
            if (!isContainsMap(noRepeatList, strStrMap)) {
                noRepeatList.add(strStrMap);
            }
        });


//        String[] keys = {"a", "b", "c"};
//        System.out.println(compareMap(map1, map2, keys));
    }

    /**
     * 检查集合中是否有指定Map
     * @param noRepeatList 不重复的集合
     * @param checkedMap
     * @return
     */
    public static boolean isContainsMap(List<Map<String, String>> noRepeatList, Map<String, String> checkedMap) {
        String[] keys = {"a", "b", "c"};
        for (Map<String, String> map : noRepeatList) {
            if (compareMap(map, checkedMap, keys)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较两个Map
     * @param map1
     * @param map2
     * @param compareKeys 需要比较的key 如果这些都相同就认为两个Map相等
     * @return
     */
    public static boolean compareMap(Map<String, String> map1, Map<String, String> map2, String[] compareKeys) {

        for (String compareKey : compareKeys) {
            if (map1.get(compareKey) == null || map2.get(compareKey) == null) {
                return false;
            }
            if (!Objects.equals(map1.get(compareKey), map2.get(compareKey))) {
                return false;
            }
        }
        return true;
    }


}
