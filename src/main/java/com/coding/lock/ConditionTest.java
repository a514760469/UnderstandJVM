package com.coding.lock;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * Condition与Lock结合使用的方式类比wait/notify与synchronized关键字的使用，它们都能实现等待通知机制。
 *
 * 在使用Condition的await类和sign类方法时，都必须先用lock提前获取锁
 *
 * @author zhanglifeng
 * @since 2021-07-02
 */
public class ConditionTest {

    @Test
    public void conditionTest2() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "await_thread");

    }
}
