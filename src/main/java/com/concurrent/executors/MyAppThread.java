package com.concurrent.executors;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 定义MyAppThread类：
 * 为线程指定名字，设置自定义UncaughtExceptionHandler为向logger中写入信息，
 * 维护一些统计信息：有多少个线程被创建和销毁。
 * @author zhanglifeng
 * @since 2020-06-23 18:07
 */
public class MyAppThread extends Thread {

    private final static String DEFAULT_NAME = "MyAppThread";

    private static final AtomicInteger created = new AtomicInteger();

    private static final AtomicInteger alive = new AtomicInteger();

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private static volatile boolean debugLifecycle = false;


    public MyAppThread(Runnable r) {
        this(r, DEFAULT_NAME);
    }

    public MyAppThread(Runnable r, String name) {
        super(r, name + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LOGGER.log(Level.SEVERE, "未捕获的异常 in thread：" + t.getName(), e);
            }
        });
    }

    @Override
    public void run() {
        boolean debug = debugLifecycle;
        if (debug) {
            LOGGER.log(Level.FINE, "Created " + getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                LOGGER.log(Level.FINE, "Exiting " + getName());
            }
        }
    }

    public static int getThreadsCreated() {
        return created.get();
    }

    public static int getThreadsAlive() {
        return alive.get();
    }

    public static boolean getDebug() {
        return debugLifecycle;
    }

    public static void setDebug(boolean debug) {
        debugLifecycle = debug;
    }
}
