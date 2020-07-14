package com.concurrent.synchronizer;

import com.concurrent.jcip.ann.ThreadSafe;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 使用 AbstractQueuedSynchronizer 实现的二元闭锁
 * @author zhanglifeng
 * @since 2020-07-13 17:51
 */
@ThreadSafe
public class OneShotLatch {

    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    /**
     * AQS 用来表示闭锁状态 关闭：0 打开：1
     */
    private class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int arg) {
            // 如果锁是开的state == 1  那么这个操作将成功，否则将失败
            return getState() == 1 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);// 现在打开闭锁
            return true;// 现在其他的线程可以获取该闭锁
        }
    }

}
