package com.effective.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 让第一个线程轮询(poll) 一个boolean域
 */
public class StopThread {

    private static volatile boolean stopRequest;

    private static synchronized boolean isStopRequest() {
        return stopRequest;
    }

    private static synchronized void stopRequest() {
        stopRequest = true;
    }

    /**
     * 1、没有同步，不能保证后台线程看到主线程对stopRequest的改变--活性失败
     *
     */
    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequest)
                i++;
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
//        stopRequest();
        stopRequest = true;
    }
}
