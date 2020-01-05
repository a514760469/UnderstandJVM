package com.generic;

import java.util.Collection;
import java.util.Objects;

/**
 * 泛型方法
 */
public class GenerifyMethod {

    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("Empty Collection");

        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return result;
    }
}
