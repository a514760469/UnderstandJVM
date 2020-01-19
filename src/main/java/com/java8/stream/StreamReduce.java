package com.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author zhanglifeng
 * @since 2020-01-19 10:40
 */
public class StreamReduce {

    public static void main(String[] args) {
        // 求和sum
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        // 没有起始值时返回为Optional类型
        Optional<Integer> sumOpt = integers.stream().reduce(Integer::sum);
        System.out.println(sumOpt.orElse(0));

        // 可以给一个起始种子值
        Integer sumInteger = integers.stream().reduce(0, Integer::sum);
        System.out.println(sumInteger);

        // 直接用sum方法
        int sum = integers.stream().mapToInt(i -> i).sum();
        System.out.println(sum);

    }

    /**
     * identity:  一个初始化的值；这个初始化的值其类型是泛型U, 注意此时Stream中元素的类型是T，与U可以不一样也可以一样
     * accumulator(累加器): 其类型是BiFunction，输入是U与T两个类型的数据，而返回的是U类型；
     * combiner(组合器): 其类型是BinaryOperator，支持的是对U类型的对象进行操作
     * 第三个参数combiner主要是使用在并行计算的场景下, 非并行时不生效
     */
    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void reduceWith3Parameter() {
        ArrayList<String> result = Stream.of("aa", "ab", "c", "ad").reduce(new ArrayList<>(), (u, t) -> {
            u.add(t);
            return u;
        }, (strings, strings2) -> strings);// 是非并行的，第三个参数实际上没有什么意义，可以指定r1或者r2为其返回值，甚至可以指定null为返回值
        System.out.println(result);

        Integer reduce = Stream.of(1, 2, 3).parallel()
                .reduce(4, (uInt, tInt) -> uInt + tInt, (integer, integer2) -> integer + integer2);
        System.out.println(reduce);// 18

        Optional<Integer> reduceOpt = Stream.of(1, 2, 3).map(n -> n + 4).reduce((i1, i2) -> i1 + i2);
        System.out.println(reduceOpt.get());
    }

    @Test
    public void reduce2() {
        List<String> strs = Arrays.asList("H", "E", "L", "L", "O");
        String reduce = strs.stream().reduce("", String::concat);
        System.out.println(reduce);

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);

        Integer minReduce = integerStream.reduce(Integer.MAX_VALUE, Integer::min);
        System.out.println(minReduce);

    }

}
