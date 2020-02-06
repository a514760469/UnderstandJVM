package com.test;

public class Borly extends Thread {

    public static void main(String[] args) {
//        new Borly().start();
        System.out.println(40 & 1);

        System.out.println(~255);

        System.out.println(1 << 0);// 1
        System.out.println(1 << 1);// 2
        System.out.println(1 << 2);// 4
        System.out.println(1 << 3);// 8

        System.out.println(1 << 0 | 1 << 1);// 3
    }


    @Override
    public void run() {
        System.out.println("run");
    }
}
