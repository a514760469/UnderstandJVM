package com.concurrent.log;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhanglifeng
 * @since 2020-06-22 15:25
 */
public class LogService {

    private static final int CAPACITY = 1000;

    private final BlockingQueue<String> queue;

    private final LoggerThread logger;

    private boolean isShutdown;// true

    private int reservations;// 保留

    public LogService(Writer writer) {
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
        this.logger = new LoggerThread(writer);
    }

    public void start() {
        logger.start();
    }

    /**
     * 存在竞态条件，判断和put不是原子操作
     */
    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException("logger 已经关闭");
            }
            ++ reservations;
        }
        queue.put(msg);
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        logger.interrupt();
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
                    try {
                        synchronized (LogService.this) {
                            if (isShutdown && reservations == 0) {
                                break;
                            }
                        }
                        String msg = queue.take();
                        synchronized (LogService.this) {
                            --reservations;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e) {
                        /*  retry  */
                    }
                }
            } finally {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LogService logWriter = new LogService(new PrintWriter(System.out));
        logWriter.start();
        Thread.sleep(1000);

        logWriter.log("hehehe");


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("JVM关闭钩子！");
            logWriter.stop();
        }));

    }
}
