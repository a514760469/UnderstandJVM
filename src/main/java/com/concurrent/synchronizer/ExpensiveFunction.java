package com.concurrent.synchronizer;

import java.math.BigInteger;

/**
 * @author zhanglifeng
 * @since 2020-06-16 10:50
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        // 长时间的计算后
        return new BigInteger(arg);
    }
}
