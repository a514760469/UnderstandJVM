package com.concurrent.synchronizer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用ConcurrentHashMap代替hashMap
 * @author zhanglifeng
 * @since 2020-06-16 10:55
 */
public class Memoizer2<A, V> implements Computable<A, V>  {

    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * 这里两个线程可能会计算的到相同的值
     */
    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
