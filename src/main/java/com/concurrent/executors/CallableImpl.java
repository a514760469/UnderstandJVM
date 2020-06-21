package com.concurrent.executors;

import java.util.concurrent.Callable;

/**
 * @author zhanglifeng
 * @since 2020-06-17 11:11
 */
public class CallableImpl implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("我耗时2秒");
        Thread.sleep(2000);
        return "success";
    }
}
