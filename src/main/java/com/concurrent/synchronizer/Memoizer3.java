package com.concurrent.synchronizer;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 使用 ConcurrentHashMap<A, Future<V>>
 * @author zhanglifeng
 * @since 2020-06-16 10:55
 */
public class Memoizer3<A, V> implements Computable<A, V>  {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * Memoizer3首先检查某个相应的计算是否已经开始，
     * 计算如果没有启动就创建一个FutureTask 并注册到map中，然后启动计算，如果已启动就等待调用结果
     */
    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run();// 在这里将调用 c.compute(arg)
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
