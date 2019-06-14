package com.jvm.allocation;

public class AllocationTest {
	private static final int _1MB = 1024 * 1024;
	
	/**
	 * VM参数：
	 * -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -verbose:gc
	   -XX:+PrintTenuringDistribution -XX:PretenureSizeThreshold=3145728
	   
	 * 
	 * -XX:+UseConcMarkSweepGC				是用CMS+ParNew收集器组合
	 * -XX:PretenureSizeThreshold=3145728	大对象直接进入老年代3M
	 * -XX:MaxTenuringThreshold=1
	 */
	@SuppressWarnings("unused")
	public static void testAllocation() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation4 = new byte[4 * _1MB];
	}
	
	@SuppressWarnings("unused")
	public static void testTenuringThreshold() {
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
	}
	
	public static void main(String[] args) {
//		testAllocation();
		testTenuringThreshold();
	}
	
}
