package com.concurrent;

/**
 * @author zhanglifeng
 * @since 2020-06-10 18:30
 * NotThreadSafe
 */
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}