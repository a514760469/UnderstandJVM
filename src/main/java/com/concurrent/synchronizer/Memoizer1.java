package com.concurrent.synchronizer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanglifeng
 * @since 2020-06-16 10:55
 */
public class Memoizer1<A, V> implements Computable<A, V>  {

    private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * 由于hashMap不是线程安全的，保守的方法对整个compute方法同步
     */
    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
