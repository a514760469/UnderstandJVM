package com.concurrent.executors;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author zhanglifeng
 * @since 2020-06-29 14:57
 */
public class Example {

    public static void main(String[] args) {


    }


    /**
     * 一般的串行循环
     */
    void processSequentially(List elements) {
        for (Object e : elements) {
            process(e);
        }
    }

    /**
     * 并行循环
     */
    void processInParallel(Executor exec, List elements) {
        for (final Object e : elements) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    process(e);
                }
            });
        }
    }

    void process(Object e) {

    }



    /**
     * 树结构
     * @param <T>
     */
    class Node<T> {

    }
}
