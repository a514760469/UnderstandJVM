package com.concurrent.executors;

import java.util.concurrent.ThreadFactory;

/**
 * @author zhanglifeng
 * @since 2020-06-23 18:03
 */
public class MyThreadFactory implements ThreadFactory {

    private final String poolName;

    MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }

}
