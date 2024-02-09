package com.concurrent.juc;

import java.util.concurrent.*;

/**
 * @author zhanglifeng
 * @since 2023-04-06
 */
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask);
        t1.start();
        System.out.println("futureTask.get() = " + futureTask.get());

//        CompletableFuture.runAsync(futureTask, command -> {});
//        CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }, command -> {});
    }
}


class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("----- call()");
        return "hello call!";
    }
}