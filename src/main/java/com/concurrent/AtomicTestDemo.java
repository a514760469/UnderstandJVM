package com.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhanglifeng
 * @since 2020-06-08 16:21
 */
public class AtomicTestDemo {

    public static void main(String[] args) {
        AtomicReference<Member> ref = new AtomicReference<>();
        Member zs = new Member("zs", 20);
        Member ls = new Member("ls", 30);
        ref.set(zs);
        ref.compareAndSet(zs, ls);
        System.out.println(ref);
    }

    static class Member {
        private String name;
        private int age;

        public Member(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "name = " + this.name + "、age = " + this.age;
        }
    }
}
