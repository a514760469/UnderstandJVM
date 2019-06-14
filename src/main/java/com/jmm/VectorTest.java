package com.jmm;

import java.util.Vector;
/**
 * 
 * @author zhanglifeng
 * 2019年3月20日-下午6:32:19
 */
public class VectorTest {
	
	private static Vector<Integer> vector = new Vector<>();
	
	public static void main(String[] args) {
		
		while (true) {
			
			for(int i = 0; i < 10; i++) {
				vector.add(i);
			}
			
			Thread removeThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					synchronized (vector) {
						for (int i = 0; i < vector.size(); i++) {
							vector.remove(i);
						}
					}
					
				}
			});
			
			Thread getThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					synchronized (vector) {
						for (int i = 0; i < vector.size(); i++) {
							System.out.println(vector.get(i));
						}
					}
				}
			});
			
			removeThread.start();
			getThread.start();
			while(Thread.activeCount() > 20);
		}
	}
}
