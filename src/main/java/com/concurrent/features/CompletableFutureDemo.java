package com.concurrent.features;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author zhanglifeng
 * @since 2021-03-24
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建异步执行任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> queryCode("中国石油"));

        // cfQuery成功后继续执行下一个任务: 实现串行操作
        CompletableFuture<Double> cf = cfQuery.thenApplyAsync(CompletableFutureDemo::fetchPrice);
        // 如果执行成功:
        cf.thenAccept((result) -> System.out.println("price: " + result));
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return 0.0;
        });

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    /**
     * 测试CompletableFuture并行
     */
    @Test
    public void testParallelization() throws InterruptedException {

        // 两个CompletableFuture执行异步查询: 任意一个返回结果，就进行下一步查询价格
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> queryCode("中国石油", "https://finance.sina.com.cn/code/"));

        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> queryCode("中国石油", "https://money.163.com/code/"));

        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);

        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> fetchPrice((String) code, "https://finance.sina.com.cn/price/"));

        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> fetchPrice((String) code, "https://money.163.com/price/"));

        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);

        cfFetch.thenAccept(result -> System.out.println("price: " + result));

        Thread.sleep(2000);
    }


    static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }


    static Double fetchPrice(String code, String url) {
        try {
            Thread.sleep((long) (Math.random() * 1000));
//            System.out.println(url + " 拿到结果");
            System.out.println(code + " query price from " + url + "...");
        } catch (InterruptedException ignore) {
        }
        return 5 + Math.random() * 20;
    }

    static String queryCode(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        return "601857";
    }

    static String queryCode(String code, String url) {
        try {
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println("query code " + code + " from " + url + "...");
//            System.out.println(url + " 拿到结果");
        } catch (InterruptedException ignored) {
        }
        return "601857";
    }
}
