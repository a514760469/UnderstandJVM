package com.concurrent.synchronizer;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 来做闭锁，测试多个线程一起执行某个任务的总耗时
 * @author zhanglifeng
 * @since 2020-06-15 17:10
 */
public class TestHarness {

    public long timeTask(int nThreads, final Runnable task) throws InterruptedException {

        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
