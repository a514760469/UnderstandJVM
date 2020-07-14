package com.concurrent.lock;

import com.concurrent.jcip.ann.ThreadSafe;

/**
 * hash表 使用16个锁
 * @author zhanglifeng
 * @since 2020-07-06 17:27
 */
@ThreadSafe
public class StripedMap {

    private static final int N_LOCKS = 16;

    private final Node[] buckets;

    private final Object[] locks;

    public StripedMap(int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++) {
            locks[i] = new Object();
        }
    }

    private static class Node {
        Node next;
        Object key;
        Object value;
    }

    private int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next) {
                if (m.key.equals(key)) {
                    return m.value;
                }
            }
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                buckets[i] = null;
            }
        }
    }
}
