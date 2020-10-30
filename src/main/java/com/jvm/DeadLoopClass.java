package com.jvm;

import java.util.ArrayList;
import java.util.List;

public class DeadLoopClass {
	private static ArrayList<String> selfActCusCommissionList = new ArrayList<>();
	private static final int JOB_SPLITTING_THRESHOLD = 20;
	static {
//		if (true) {
//			System.out.println(Thread.currentThread() + "init DeadLoopClass");
//			while (true) {
//
//			}
//		}
		for (int i = 0; i < 21; i++) {
			selfActCusCommissionList.add("x" + i);
		}
		System.out.println(selfActCusCommissionList);
	}

	public static void main(String[] args) {

//		for (int i = 0, j = selfActCusCommissionList.size(); i < j; i++) {
//			System.out.println("i:" + selfActCusCommissionList.get(i));
//			if ((i != 0 && i % JOB_SPLITTING_THRESHOLD == 0 ) || i == j - 1) {
//				System.out.println("分页！");
//			}
//		}


		List<String> hrdeptList = new ArrayList<>();
		hrdeptList.add("a");
		hrdeptList.add("1");
		String join = String.join(",", hrdeptList);
		System.err.println(join);
	}
}
