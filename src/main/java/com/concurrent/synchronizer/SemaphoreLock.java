package com.concurrent.synchronizer;

import com.concurrent.jcip.ann.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类似juc.Semaphore的另一种实现形式
 *
 * @author zhanglifeng
 * @since 2020-07-13 15:44
 */
@ThreadSafe
public class SemaphoreLock {

    private final Lock lock = new ReentrantLock();

    private final Condition permitsAvailable = lock.newCondition();

    private int permits;

    SemaphoreLock(int initialPermits) {
        lock.lock();
        try {
            permits = initialPermits;
        } finally {
            lock.unlock();
        }
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits <= 0) {
                permitsAvailable.await();
            }
            --permits;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            ++permits;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }
}
