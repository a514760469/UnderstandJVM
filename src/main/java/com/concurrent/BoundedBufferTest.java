package com.concurrent;

import junit.framework.TestCase;

/**
 * @author zhanglifeng
 * @since 2020-07-07 14:17
 */
public class BoundedBufferTest extends TestCase {

    private static final long LOCKUP_DETECT_TIMEOUT = 1000;
    private static final int CAPACITY = 10000;
    private static final int THRESHOLD = 10000;

    void testIsFullAfterPuts() throws InterruptedException {
        // 串行测试
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        System.out.println(bb.isEmpty());
        System.out.println(bb.isFull());
    }

    void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread() {
            public void run() {
                try {
                    int unused = bb.take();
                    fail();// 如果执行到这里表示出现错误
                } catch (InterruptedException success) { }
            }
        };

        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (InterruptedException e) {
            fail();
        }
    }
}
