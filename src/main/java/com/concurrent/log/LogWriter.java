package com.concurrent.log;

import com.concurrent.jcip.ann.NotThreadSafe;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 多生产者单消费者，日志线程处理，记录日志到队列BlockingQueue，
 * 消费慢可能阻塞。
 * @author zhanglifeng
 * @since 2020-06-22 15:25
 */
public class LogWriter {

    private static final int CAPACITY = 1000;

    private final BlockingQueue<String> queue;

    private final LoggerThread logger;

    private boolean shutdownRequest;// true

    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
        this.logger = new LoggerThread(writer);
    }

    public void start() {
//        logger.setUncaughtExceptionHandler(new UEHLogger());
        logger.start();
    }

    /**
     * 存在竞态条件，判断和put不是原子操作
     */
    public void log(String msg) throws InterruptedException {
        if (!shutdownRequest) {
            queue.put(msg);
        } else {
            throw new IllegalStateException("logger 已经关闭");
        }
    }

    public void cancel() {
        shutdownRequest = true;
        // ...
    }

    private class LoggerThread extends Thread {
        private final PrintWriter writer;

        private LoggerThread(Writer writer) {
            this.writer = new PrintWriter(writer, true);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    writer.println(queue.take());
                    throw new RuntimeException("测试测试");
                }
            } catch (InterruptedException ignored) {
            } finally {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LogWriter logWriter = new LogWriter(new PrintWriter(System.out));

        logWriter.start();
        Thread.sleep(1000);

        logWriter.log("hehehe");
    }
}
