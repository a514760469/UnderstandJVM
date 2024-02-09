package com.concurrent;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author zhanglifeng
 * @since 2020-06-08 16:21
 */
public class AtomicTestDemo {

    public static void main(String[] args) {
//        LongAdder longAdder = new LongAdder();
//        longAdder.increment();
//        longAdder.add(1);


        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> y + x, 0);
        longAccumulator.accumulate(4);
        System.out.println("longAccumulator.get() = " + longAccumulator.get());

        StampedLock lock = new StampedLock();
        long l = lock.writeLock();
        System.out.println("l = " + l);
        lock.unlockWrite(l);


//        lock.tryOptimisticRead();

    }
}
