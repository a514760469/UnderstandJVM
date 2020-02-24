package com.test;

import java.util.*;

public class Borly extends Thread {

    public static void main(String[] args) {
//        new Borly().start();
//        System.out.println(40 & 1);
//
//        System.out.println(~255);
//
//        System.out.println(1 << 0);// 1
//        System.out.println(1 << 1);// 2
//        System.out.println(1 << 2);// 4
//        System.out.println(1 << 3);// 8
//
//        System.out.println(1 << 0 | 1 << 1);// 3
//
//        ArrayList<String> words = new ArrayList<>();
//        words.sort(Comparator.comparingInt(String::length));

//        String s = "1dsadahei21913ukcmzxc1";
//        System.out.println(countIncr(s));


        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("a", 1);

        System.out.println(linkedHashMap);
    }

    private static Map<Character, Integer> countIncr(String s) {
        Map<Character, Integer> hash = new HashMap<>();
        for (char c : s.toCharArray()) {
            hash.merge(c, 1, Integer::sum);
        }
        return hash;
    }

    @Override
    public void run() {
        System.out.println("run");
    }
}
