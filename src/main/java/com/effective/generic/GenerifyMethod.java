package com.effective.generic;

import org.junit.Test;

import java.util.*;

/**
 * 泛型方法
 */
public class GenerifyMethod {

    public static <T extends Comparable<? super T>> T max(Collection<? extends T> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("Empty Collection");

        T result = null;
        for (T e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return result;
    }

    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    @Test
    public void testMax() {
        List<Number> list = new ArrayList<>();
        list.add(1);
        list.add(3.0);
        list.add(2L);
        System.out.println(list);
    }

    public static void main(String[] args) {
        Set<Integer> intSet = new HashSet<>();
        intSet.add(1);
        intSet.add(3);
        intSet.add(5);
        Set<Double> doubleSet = new HashSet<>();
        doubleSet.add(2.0);
        doubleSet.add(4.0);
        doubleSet.add(6.0);

        Set<Number> numberSet = GenerifyMethod.<Number>union(intSet, doubleSet);

        System.out.println(numberSet);
    }
}
