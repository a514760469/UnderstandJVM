package com.jmm;

/**
 * @author zhanglifeng
 * @since 2020-07-14 16:54
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("t1 sleep出现InterruptedException");
                    e.printStackTrace();
                    return;
                }
                System.out.println("t1 finish");
            }
        };
        t1.start();
        Thread.sleep(1000);
//        t1.interrupt();
        t1.join(1000);


        System.out.println("main finish");
    }
    
}
