package com.java8.function;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author zhanglifeng
 * @date 2019/11/8/0008
 */
public class CollectorsUtil {

    static final Set<Collector.Characteristics> CH_NOID = Collections.emptySet();

    /**
     * 依葫芦画瓢
     * @see java.util.stream.Collectors#summingInt(java.util.function.ToIntFunction)
     * @param mapper
     * @param <T>
     * @return
     */
    public static <T> Collector<T, ?, BigDecimal> summingBigDecimal(Function<? super T, BigDecimal> mapper) {

        /*
         * arg[0]创建一个计算用的容器
         * arg[1]为计算逻辑
         * arg[2]为合并逻辑
         * arg[3]为返回最终计算值
         * arg[4]为空Set(不知道干什么用。。)
         */
        return new CollectorImpl<>(() ->
                new BigDecimal[]{ BigDecimal.ZERO },
                (a, t) -> a[0] = a[0].add(mapper.apply(t)),
                (a, b) -> {
                    a[0] = a[0].add(b[0]);
                    return a;
                }, a -> a[0], CH_NOID);
    }

    /**
     * Simple implementation class for {@code Collector}.
     *
     * @param <T> the type of elements to be collected
     * @param <R> the type of the result
     */
    static class CollectorImpl<T, A, R> implements Collector<T, A, R> {
        private final Supplier<A> supplier;
        private final BiConsumer<A, T> accumulator;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Set<Characteristics> characteristics;

        CollectorImpl(Supplier<A> supplier,
                      BiConsumer<A, T> accumulator,
                      BinaryOperator<A> combiner,
                      Function<A,R> finisher,
                      Set<Characteristics> characteristics) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
            this.characteristics = characteristics;
        }


        @Override
        public BiConsumer<A, T> accumulator() {
            return accumulator;
        }

        @Override
        public Supplier<A> supplier() {
            return supplier;
        }

        @Override
        public BinaryOperator<A> combiner() {
            return combiner;
        }

        @Override
        public Function<A, R> finisher() {
            return finisher;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return characteristics;
        }
    }
}
