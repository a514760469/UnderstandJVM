package com.concurrent.synchronizer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore为容器设置边界
 * 使Set容器变成有界阻塞容器
 * @author zhanglifeng
 * @since 2020-06-15 18:13
 */
public class BoundedHashSet<T> {

    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.semaphore = new Semaphore(bound);
    }


    public boolean add(T t) throws InterruptedException {
        semaphore.acquire();// 获取许可，会阻塞直到有许可
        boolean wasAdded = false;
        try {
            wasAdded = set.add(t);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                semaphore.release();
            }
        }
    }

    public boolean remove(T t) {
        boolean removed = set.remove(t);
        if (removed) {
            semaphore.release();
        }
        return removed;
    }


    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<Integer> boundedHashSet = new BoundedHashSet<>(3);
        boundedHashSet.add(1);
        boundedHashSet.add(2);
        boundedHashSet.add(3);
//        boundedHashSet.remove(3);
        boundedHashSet.add(4);// blocking
        System.out.println(boundedHashSet.set);
    }
}
