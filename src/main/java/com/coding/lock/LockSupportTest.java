package com.coding.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zhanglifeng
 * @since 2021-07-02
 */
public class LockSupportTest {

    private void lockSupport() {
        LockSupport.park();
        System.out.println("线程继续执行");
    }

    public static void main(String[] args) throws InterruptedException {
        LockSupportTest lockSupportTest = new LockSupportTest();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockSupportTest.lockSupport();
            }
        }, "park_thread");

        thread1.start();
        Thread.sleep(3000);
//        System.out.println("开始中断线程");
//        thread1.interrupt();

        // unpark 方法唤醒(前面的中断也可以认为是唤醒的一种方式)
        System.out.println("开始唤醒线程");
        LockSupport.unpark(thread1);
    }
}
