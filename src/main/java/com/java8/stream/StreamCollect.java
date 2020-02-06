package com.java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhanglifeng
 * @since 2020-01-19 11:41
 */
public class StreamCollect {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("H", "E", "L", "L", "O");

        // 遇上相同key和null的value都会抛出异常
//        Map<String, String> map = list.stream().collect(Collectors.toMap(k -> k, v -> v));

        // 两个Key相同是，只有一个key存在, value 由自己处理：(oldV, newV) -> newV)
        Map<String, String> map = list.stream()
                .collect(Collectors.toMap(k -> k, v -> v, (oldV, newV) -> newV));

        System.out.println(map);

        // 提供了mergeFunction和mapSupplier 调用者可以自定义希望返回什么类型的Map
        List<String> perhapsNullList = Arrays.asList("H", "E", "L", "L", "O");
        Map<String, String> fourParameter = perhapsNullList.stream()
                .collect(Collectors.toMap(k -> k, v -> v, (oldV, newV) -> newV, LinkedHashMap::new));
        System.out.println(fourParameter);

    }


    @Test
    public void intSummaryTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        IntSummaryStatistics summary = list.stream().collect(Collectors.summarizingInt(value -> value));
        System.out.println(summary.getCount());
        System.out.println(summary.getAverage());
        System.out.println(summary.getSum());
    }

    @Test
    public void joiningTest() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        String collect = list.stream().collect(Collectors.joining("=", "=>", "<="));
        System.out.println(collect);
    }

    @Test
    public void collectingAndThen() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        @SuppressWarnings("SimplifyStreamApiCallChains")
        Optional<Integer> maxOpt = list.stream().collect(Collectors.maxBy(Integer::compare));

        System.out.println(maxOpt);
        Integer maxAndThen = list.stream().collect(Collectors.collectingAndThen(Collectors.maxBy(Integer::compare), Optional::get));
        System.out.println(maxAndThen);
    }

    @Test
    public void partitioningBy() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 5, 5, 6, 9);
        Map<Boolean, List<Integer>> partition = list.stream().collect(Collectors.partitioningBy(x -> x >= 4));
        System.out.println(partition);

        Map<Boolean, Map<Boolean, List<Integer>>> partition2 = list.stream().collect(Collectors.partitioningBy(x -> x >= 4, Collectors.partitioningBy(x -> x > 6)));
        System.out.println(partition2);

        Map<Boolean, Map<Integer, List<Integer>>> partition3 = list.stream().collect(Collectors.partitioningBy(x -> x >= 4, Collectors.groupingBy(x -> x)));
        System.out.println(partition3);
    }

    /**
     * 跟map操作类似
     */
    @Test
    public void mapping() {
        @SuppressWarnings("SimplifyStreamApiCallChains")
        String str = Stream.of("a", "b", "c").collect(Collectors.mapping(String::toUpperCase, Collectors.joining(",")));
        System.out.println(str);
    }

    @Test
    public void streamStatic() {
        // 要合并提取两个或者更多的List集合的时候，没必要先合并集合，再处理流
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 3, 2);
        Stream.concat(list1.stream(), list2.stream()).forEach(System.out::print);
    }

}
