package com.algorithm.sort;
/**
 * 插入排序
 * @author zhanglifeng
 */
public class InsertionSort {
	
	public static void main(String[] args) {
		int[] a = { 5, 2, 4, 6, 1, 3 };
		insertionSort(a);
		
		for (int i : a) {
			System.out.print(i + ", ");
		}
	}
	/**
	 * 5  2  4  6  1  3
	 * 一般的插入排序
	 * @param a
	 */
	public static void insertionSort(int[] a) {
		int temp; // 临时变量
		// 外层循环控制需要排序的趟数(从1开始因为将第0位看成了有序数据)
		for (int i = 1; i < a.length; i++) {  
			temp = a[i]; // 将当前元素拿出去
			while ( i >= 1 && a[i - 1] > temp ) {
				// 有序数组后移
				a[i] = a[i - 1];
				i--;
			}
			// 退出循环说明找到了合适位置
			a[i] = temp;
		}
	}
	
}
