package com.concurrent.synchronizer;

import com.concurrent.jcip.ann.ThreadSafe;

import java.util.concurrent.CountDownLatch;

/**
 * 阻塞的携带结果的闭锁
 * @author zhanglifeng
 * @since 2020-06-30 16:52
 */
@ThreadSafe
public class ValueLatch<T> {

    private T value = null;

    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return done.getCount() == 0;
    }

    public synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue;
            done.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        done.await();
        synchronized (this) {
            return value;
        }
    }
}
