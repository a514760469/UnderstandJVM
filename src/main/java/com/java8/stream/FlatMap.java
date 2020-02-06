package com.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMap {

    /**
     * ["Hello","World"] 返回 ["H","e","l","o","W","r","d"]
     * @param args n
     */
    public static void main(String[] args) {

        String[] strings = {"Hello", "World"};

        List<String> stringList = Arrays.stream(strings)
                .map(str -> {
                    System.out.println("m1:" + str);
                    return str.split("");
                }).flatMap(strs -> {
                    for (String s : strs) {
                        System.out.println(s);
                    }
                    return Arrays.stream(strs);
                }).collect(Collectors.toList());

        System.out.println(stringList);

    }

    @Test
    public void compareMapAndFlatMap() {
        List<String> list = Arrays.asList("aaa", "bbb", "ddd", "eee", "ccc");
        list.stream().map(s -> {
            List<Character> characters = new ArrayList<>();
            for (char c : s.toCharArray()) {
                characters.add(c);
            }
            return characters.stream();
        }).forEach(characterStream -> characterStream.forEach(System.out::print));

        System.out.println();
        list.stream().flatMap(s -> {
            List<Character> characters = new ArrayList<>();
            for (char c : s.toCharArray()) {
                characters.add(c);
            }
            return characters.stream();
        }).forEach(System.out::print);
    }

    /**
     * 给定一个需求：给定单词列表["Hello","World"]，要返回列表["H","e","l", "o","W","r","d"]
     */
    @Test
    public void compareMapAndFlatMap2() {
        List<String> list = Arrays.asList("hello", "world");
        list.stream().flatMap(s -> Arrays.stream(s.split(""))).distinct().forEach(System.out::print);
    }

}
