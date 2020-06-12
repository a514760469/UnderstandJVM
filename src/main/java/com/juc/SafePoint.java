package com.juc;

import com.juc.jcip.ann.ThreadSafe;

/**
 * @author zhanglifeng
 * @since 2020-06-12 10:33
 * 线程安全且可变的Point类
 */
@ThreadSafe
public class SafePoint {

    public int x, y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
