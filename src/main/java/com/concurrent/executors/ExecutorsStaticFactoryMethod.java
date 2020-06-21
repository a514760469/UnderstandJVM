package com.concurrent.executors;

import java.util.Collections;
import java.util.concurrent.*;

/**
 *
 * new ThreadPoolExecutor();
 *
 * @author zhanglifeng
 * @since 2020-06-16 15:33
 */
public class ExecutorsStaticFactoryMethod {

    public static void main(String[] args) {
        // 将创建一个固定长度的线程池，每当提交一个任务就创建一个线程，直到达到最大数量.如果某个线程Exception，会补充一个。
//        Executor fixedThreadPool = Executors.newFixedThreadPool(10);

        // 创建一个可缓存的线程池，如果容量超过处理规模，则回收线程，如果需求增加，则可以添加新的线程，线程池规模不限制。
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 创建单线程的线程池。如果发生异常，创建另一个来替代。SingleThreadExecutor能确保任务顺序执行(FIFO、LIFO)
        ExecutorService singleThread = Executors.newSingleThreadExecutor();

        // 创建固定长度的线程池，以延迟或定时的方式来执行任务
//        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);

        // ExecutorService 生命周期有3种状态，运行关闭和已终止。 shutdown()平缓关闭
        // 关闭后提交的任务由拒绝执行处理器(RejectedExecutionHandler)处理 它会抛出RejectedExecutionException
        Future<String> future = singleThread.submit(new CallableImpl());
        try {
//            singleThread.invokeAll(Collections.emptyList(), 1, TimeUnit.MINUTES);
//            future.isDone();
            System.out.println("先get：" + future.get(3, TimeUnit.SECONDS));// 超时抛出TimeoutException
//            System.out.println("先get：" + future.get());
            Thread.sleep(100);
            System.out.println("再get：" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            future.cancel(true);
        }
        System.out.println(future.cancel(true));
        singleThread.shutdown();
    }

}
