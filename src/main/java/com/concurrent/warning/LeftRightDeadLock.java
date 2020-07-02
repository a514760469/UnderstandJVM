package com.concurrent.warning;

/**
 * 当有两个线程分别调用leftRight和rightLeft 可能出现死锁，
 * 锁顺序死锁：获取锁的顺序不同，如果都以相同的顺序获取锁就不会出现死锁了。
 * @author zhanglifeng
 * @since 2020-07-01 15:13
 */
public class LeftRightDeadLock {

    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                // doSomething
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                // doSomething
            }
        }
    }

}
