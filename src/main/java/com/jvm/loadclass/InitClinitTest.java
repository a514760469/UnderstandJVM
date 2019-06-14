package com.jvm.loadclass;

public class InitClinitTest {
	
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread() + " start ");
				DeadLoopClass class1 = new DeadLoopClass();
				System.out.println(Thread.currentThread() + " run over" + class1);
			}
		};
		
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable);
		thread1.start();
		thread2.start();
	}
}

class DeadLoopClass {
	static {
		if(true) {
			System.out.println(Thread.currentThread() + "init DeadLoopClass");
			while(true) {
			}
		}
	}
}
