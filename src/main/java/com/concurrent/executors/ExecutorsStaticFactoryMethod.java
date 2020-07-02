package com.concurrent.executors;

import java.util.concurrent.*;

/**
 *
 * new ThreadPoolExecutor();
 *
 * @author zhanglifeng
 * @since 2020-06-16 15:33
 */
public class ExecutorsStaticFactoryMethod {

    /**
     * 包装一层不可配置
     * ExecutorService executorService = Executors.unconfigurableExecutorService(singleThread);
     *
     */
    public static void main(String[] args) {
        // 将创建一个固定长度的线程池，每当提交一个任务就创建一个线程，直到达到最大数量.如果某个线程Exception，会补充一个。
        // 默认使用无界的LinkedBlockingQueue，
        Executor fixedThreadPool = Executors.newFixedThreadPool(10);

        // 创建一个可缓存的线程池，如果容量超过处理规模，则回收线程，如果需求增加，则可以添加新的线程，线程池规模不限制。
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();// 是一种很好的默认选择

        // 创建单线程的线程池。如果发生异常，创建另一个来替代。SingleThreadExecutor能确保任务顺序执行(FIFO、LIFO)
        ExecutorService singleThread = Executors.newSingleThreadExecutor();

        // 创建固定长度的线程池，以延迟或定时的方式来执行任务
//        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);


        // 自定义ThreadPool，
        /*
         * 使用有界队列 ArrayBlockingQueue(10) 或 SynchronousQueue (使用移交策略，不是直接放在队列中， 线程池要无界，例：newCachedThreadPool)
         * 使用 new PriorityBlockingQueue<>(); 可以设置优先级;
         * 饱和策略：CallerRunsPolicy
         */
        ThreadPoolExecutor customThreadPool =
                new ThreadPoolExecutor(
                        0,
                        Integer.MAX_VALUE,
                        1,
                        TimeUnit.SECONDS,
                        new SynchronousQueue<>(),
                        new MyThreadFactory("customThreadPool"),
                        new ThreadPoolExecutor.CallerRunsPolicy());

        // 将自定义的线程池包装使其策略不可被修改
        ExecutorService unconfigurable = Executors.unconfigurableExecutorService(customThreadPool);



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
        System.out.println(future.cancel(true));// 通过任务的future来取消他们，param如果true表示应该被中断，如果false,让其执行完成。
        singleThread.shutdown();
//        singleThread.shutdownNow();// 关闭当前任务，返回所有尚未启动的任务清单
    }















    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            System.out.println("接下来任务将会取消");// 到 finally
        } catch (ExecutionException e) {
            throw new InterruptedException();
        } finally {
            // 如果任务结束那么执行取消操作也不会有任何影响
            task.cancel(true);
        }

    }

}
