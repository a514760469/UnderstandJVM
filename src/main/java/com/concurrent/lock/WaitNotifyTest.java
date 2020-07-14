package com.concurrent.lock;

/**
 * wait: 当前线程释放锁，休息去了，等待notify的唤醒
 * wait必须在synchronized中使用, 否则会抛出IllegalMonitorStateException（正在等待的对象没有锁）
 * notify 也必须在synchronized中使用
 * @author zhanglifeng
 * @since 2020-07-09 18:05
 */
public class WaitNotifyTest {

    public static void main(String[] args) throws InterruptedException {
//        ThreadA t1 = new ThreadA("t1");
//
//        synchronized (t1) {
//            System.out.println(Thread.currentThread().getName() + " start t1");
//            t1.start();
//            System.out.println(Thread.currentThread().getName() + " wait()");
//            t1.wait();
//            System.out.println(Thread.currentThread().getName() + " continue");
//        }
        waitNotifyTest();
    }


    private static void waitNotifyTest() throws InterruptedException {
        Object t = new Object();
        Thread t2 = new Thread(() -> {
            synchronized (t) {
                t.notify();
                System.out.println("唤醒了");
            }
        });
        t2.start();

        synchronized (t) {
            System.out.println("wait了");
            t.wait(2000);
        }

        System.out.println("finish");
    }

}
