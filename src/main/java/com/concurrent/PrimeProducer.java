package com.concurrent;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanglifeng
 * @since 2020-06-19 18:01
 */
public class PrimeProducer extends Thread {

    /**
     * 阻塞的队列 put时如果满了就阻塞
     */
    private final BlockingQueue<BigInteger> queue;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException ignored) {
            System.err.println("InterruptedException");
            Thread.currentThread().interrupt();
        }
    }


    /**
     * 取消时使用中断
     */
    public void cancel() {
        interrupt();
    }


    public Collection<BigInteger> get() {
        return new ArrayList<>(queue);
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(3, true);
        PrimeProducer producer = new PrimeProducer(queue);
        producer.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } finally {
            producer.cancel();
        }
        System.out.println(producer.get());
    }
}
