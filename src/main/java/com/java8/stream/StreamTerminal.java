package com.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanglifeng
 * @since 2020-01-19 9:50
 * stream 流终止操作
 */
public class StreamTerminal {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        // 并行流 无序
        list.parallelStream().forEach(System.out::print);
//        list.stream().parallel().forEach(System.out::print);
        System.out.println();
        // 保证有序的操作
        list.parallelStream().forEachOrdered(System.out::print);
        List<Integer> collect = list.parallelStream().collect(Collectors.toList());
        System.out.println(collect);


    }

}
