package com.effective.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 返回 集合(a, b, c) 的所有子集
 *
 * range(int startInclusive, int endExclusive)
 *  包含startInclusive 不包含endInclusive
 * rangeClosed(int startInclusive, int endInclusive)
 *  包含startInclusive 包含endInclusive
 */
public class SubList {

    public static <E> Stream<List<E>> of(List<E> list) {
        return Stream.concat(Stream.of(Collections.emptyList()), prefixes(list).flatMap(SubList::suffixes));
    }

    private static <E> Stream<List<E>> prefixes(List<E> list) {
        return IntStream.rangeClosed(1, list.size()).mapToObj(end -> list.subList(0, end));
    }

    private static <E> Stream<List<E>> suffixes(List<E> list) {
        return IntStream.range(0, list.size()).mapToObj(start -> list.subList(start, list.size()));
    }

    /**
     * 嵌套for循环翻译
     * (int) Math.signum(start) 代替1, 即可打印空集合
     *  当start为0时 Math.signum(start) = 0；
     */
    public static <E> Stream<List<E>> of2(List<E> list) {
        return IntStream.range(0, list.size()).mapToObj(
                start -> IntStream.rangeClosed(start + (int) Math.signum(start), list.size())
                        .mapToObj(end -> list.subList(start, end))
        ).flatMap(x -> x);
    }

    public static void main(String[] args) {
        List<String> src = Arrays.asList("a", "b", "c");
        of(src).forEach(System.out::println);

        System.out.println("============2============");
        for (int start = 0; start <= src.size(); start++) {
            for (int end = start + (int) Math.signum(start); end <= src.size(); end++) {
                System.out.println(src.subList(start, end));
            }
        }

        System.out.println("============3============");

        of2(src).forEach(System.out::println);
    }


}
