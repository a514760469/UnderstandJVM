package com.concurrent.test;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhanglifeng
 * @since 2023-04-11
 */
public class LockSupportDemo {


    /**
     * 1、无锁块要求
     * 2、先唤醒后等待也支持
     * unsafe 实现
     */
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "\t === come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t === 被唤醒");
        }, "t1");
        t1.start();

//        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t === 发出通知");
            LockSupport.unpark(t1);
        }, "t2").start();
    }


    /**
     * 1、condition.await() condition.signal() 必须在 lock.lock() unlock 块中
     * 2、顺序 先condition.await() 后condition.signal()  不能反了
     */
    @Test
    public void conditionWaitAndNotify() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t === come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t === 被唤醒");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t === 发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();

        // TimeUnit.SECONDS.sleep(5);

    }


    /**
     * 1、wait notify 必须在 synchronized 块中
     * 2、顺序 先wait 后notify
     */
    @Test
    public void synchronizedObjectLockWaitAndNotify() throws InterruptedException {
        final Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "\t === come in");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "\t === 被唤醒");
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            synchronized (lock) {
                lock.notify();
                System.out.println(Thread.currentThread().getName() + "\t === 发出通知");
            }
        }, "t2").start();
    }

}
