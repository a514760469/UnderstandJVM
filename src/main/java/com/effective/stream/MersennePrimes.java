package com.effective.stream;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * 梅森素数
 * 形式为 ：2<sup>{@code p}</sup> - 1 的数字，如果p是一个素数，相应的梅森数字也是素数；那么它就是一个梅森素数。
 */
public class MersennePrimes {

    private final static BigInteger TWO = BigInteger.valueOf(2);
    private final static BigInteger ONE = BigInteger.ONE;

    static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }

    public static void main(String[] args) {
        primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(10)
                .forEach(mp -> System.out.println(mp.bitLength() + ": " + mp));
    }
}
