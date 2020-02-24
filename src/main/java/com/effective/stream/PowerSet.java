package com.effective.stream;

import java.util.*;

/**
 * 47
 * 返回指定集合的幂集
 */
public class PowerSet {

    public static <E> Collection<Set<E>> of(Set<E> s) {
        List<E> src = new ArrayList<>(s);

        // 不能超过30个元素，因为2的n次方要小于 INTEGER.MAX_VALUE
        if (src.size() > 30)
            throw new IllegalArgumentException("Set too big " + s);

        return new AbstractList<Set<E>>() {
            @Override
            public Set<E> get(int index) {
                Set<E> result = new HashSet<>();
                for (int i = 0; index != 0; i++, index >>= 1) {
                    if ((index & 1) == 1) {
                        result.add(src.get(i));
                    }
                }
                return result;
            }

            @Override
            public int size() {
                return 1 << src.size();// 2 的 size 次幂
            }

            @Override
            public boolean contains(Object o) {
                return o instanceof Set && src.containsAll((Set) o);
            }
        };
    }

    public static void main(String[] args) {
        int size = 3;

        System.out.println( 1 << size);

        int index = 1;
        index >>= 1;
        System.out.println(index);

//        System.out.println((5 & 1) == 1);

        for (index = 4; index != 0; index >>= 1) {
            System.out.println(index);
        }

        // testPowerSet
        HashSet<String> set = new HashSet<>(Arrays.asList("a", "b", "c"));
        List<Set<String>> collection = (List<Set<String>>) PowerSet.of(set);
        System.out.println(collection.get(1));

    }

}
