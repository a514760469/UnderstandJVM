package com.generic;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 玩具堆栈实现
 * @param <E>
 */
public class Stack<E> {

    private E[] elements;

    private int size;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * elements数组只可以包含E的实例 通过push(E e), 这足以确保类型安全。
     * 但是数组的运行时类型不是E[] 而是Object[]。
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = elements[--size];
        elements[size] = null;// 消除引用 元素可以被gc掉
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
