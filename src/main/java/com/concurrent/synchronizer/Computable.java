package com.concurrent.synchronizer;

/**
 * @author zhanglifeng
 * @since 2020-06-16 10:49
 */
public interface Computable<A, V> {

    V compute (A arg) throws InterruptedException;
}
