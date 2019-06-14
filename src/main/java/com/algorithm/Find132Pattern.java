package com.algorithm;

public class Find132Pattern {
	
	public static void main(String[] args) {
		int[] a = {3, 5, 0, 3, 4}; 
		System.out.println(find132pattern(a));
	}
	
	/**
	 *  1   2   3   4	5
	 *  i   j   k 
	 */
	public static boolean find132pattern(int[] a) {
       
        for (int i = 0; i < a.length; i++ ) {
        	for (int j = i + 1; j < a.length; j++ ) {
        		for (int k = j + 1; k < a.length; k++ ) {
        			if(a[i] < a[k] && a[k] < a[j]) {
                        return true;
                    }
        		}
        	}
        }
        return false;
    }
}
