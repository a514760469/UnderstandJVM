package com.concurrent;

import com.concurrent.jcip.ann.ThreadSafe;

import java.util.concurrent.Semaphore;

/**
 * 基于信号量的有界缓存，
 * （实际情况使用ArrayBlockingQueue、LinkedBlockingQueue）
 * @author zhanglifeng
 * @since 2020-07-07 14:17
 */
@ThreadSafe
public class BoundedBuffer<E> {

    private final Semaphore availableItems;
    private final Semaphore availableSpaces;

    private final E[] items;
    private int putPosition;
    private int takePosition;

    @SuppressWarnings("unchecked")
    public BoundedBuffer(int capacity) {
        this.availableItems = new Semaphore(0);
        this.availableSpaces = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException {
        availableSpaces.acquire();
        doInsert(x);
        availableItems.release();
    }

    public E take() throws InterruptedException {
        availableItems.acquire();// 从availableItems中获得一个许可
        E e = doExtract();
        availableSpaces.release();
        return e;
    }

    private synchronized void doInsert(E x) {
        int i = putPosition;
        items[i] = x;
        putPosition = ( ++i == items.length) ? 0 : i;
    }

    private synchronized E doExtract() {
        int i = takePosition;
        E x = items[i];
        items[i] = null;
        takePosition = (++i == items.length) ? 0 : i;
        return x;
    }

}
