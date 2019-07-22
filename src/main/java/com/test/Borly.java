package com.test;

public class Borly extends Thread {

    public static void main(String[] args) {
//        new Borly().start();
        System.out.println(40 & 1);

        System.out.println(~255);
    }


    @Override
    public void run() {
        System.out.println("run");
    }
}
