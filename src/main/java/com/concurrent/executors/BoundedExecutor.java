package com.concurrent.executors;

import com.concurrent.jcip.ann.ThreadSafe;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * @author zhanglifeng
 * @since 2020-06-23 17:52
 */
@ThreadSafe
public class BoundedExecutor {

    private final Executor exec;

    private final Semaphore semaphore;


    public BoundedExecutor(Executor exec, int bound) {
        this.exec = exec;
        this.semaphore = new Semaphore(bound);
    }

    public void submitTask(final Runnable runnable)
            throws InterruptedException {
        semaphore.acquire();
        try {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        semaphore.release();
                    }

                }
            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }

    }

}
