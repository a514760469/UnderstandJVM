package com.java8.stream;

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
}
