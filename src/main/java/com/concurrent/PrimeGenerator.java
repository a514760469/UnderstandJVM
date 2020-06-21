package com.concurrent;

import com.concurrent.jcip.ann.GuardedBy;
import com.concurrent.jcip.ann.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 枚举素数, cancelled 标识是否取消
 * @author zhanglifeng
 * @since 2020-06-19 15:03
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {

    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();

    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    /**
     * 在finally块中调用此方法，否则程序可能一直停不下来消耗CPU
     */
    public void cancel() {
        this.cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }


    public static void main(String[] args) throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }

        System.out.println(generator.get().size());
    }
}
