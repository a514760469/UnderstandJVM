package com.effective.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 新增方法：按顺序将元素放入栈
     * @param src
     */
    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    /**
     * 弹出所有元素到dst集合
     * @param dst
     */
    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    /**
     * 测试main
     * @param args
     */
    public static void main(String[] args) {
        Stack<Number> stack = new Stack<>();
        stack.push(1);
        Iterable<Integer> itInt = new ArrayList<Integer>() {
            {
                add(2);
                add(6);
            }
        };
        System.out.println(itInt);
        stack.pushAll(itInt);
//        System.out.println(stack.pop());

        //
        Collection<Object> objects = new ArrayList<>();
        stack.popAll(objects);

        System.out.println(objects);


    }
}
