package com.effective.stream;

import java.math.BigInteger;
import java.util.stream.LongStream;

/**
 * 测试从并行Stream中收益
 */
public class PrimeCounting {
    /**
     * 增加 .parallel() 时间缩短
     * @param n
     * @return
     */
    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
//                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(pi((long) Math.pow(10, 7)));
        System.err.println((System.currentTimeMillis() - start));
    }
}
