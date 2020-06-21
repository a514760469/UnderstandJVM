package com.test;

import java.util.Date;

public class NullTest {

    private Integer i;

    private Date date;

    public int getI() {
        return i;
    }

    public Date getDate() {
        return date;
    }

    public static void main(String[] args) {
//        new Thread(System.out::println).start();
//        ExecutorService exec = Executors.newCachedThreadPool();

        System.out.println(sum());
    }

    static int sum(int... args){
        if (args instanceof int[]) {
            System.out.println("111");
        }

        return 1;
    }
}
