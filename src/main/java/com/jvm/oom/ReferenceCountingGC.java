package com.jvm.oom;
/**
 * JVM的GC日志的主要参数包括如下几个：
 * -XX:+PrintGC 						输出GC日志
 * -XX:+PrintGCDetails 					输出GC的详细日志
 * -XX:+PrintGCTimeStamps 				输出GC的时间戳（以基准时间的形式）
 * -XX:+PrintGCDateStamps 				输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
 * -XX:+PrintHeapAtGC 					在进行GC的前后打印出堆的信息
 * -XX:+PrintGCApplicationStoppedTime   输出GC造成应用暂停的时间
 * -Xloggc:../logs/gc.log 				日志文件的输出路径
 * 
 * -XX:+PrintFlagsInitial 				查看虚拟机初始化参数
 * 
 * -XX:MaxPermSize=8M 	(Java8不支持)
 * -XX:MetaspaceSize=128M				初始Metaspace大小元空间(Java 8)
 * 
 * -XX:+PrintGCDetails -verbose:gc -verbose:class 
 */
public class ReferenceCountingGC {

	public Object instance = null;
	
	private static final int _1MB = 1024 * 1024;
	
	@SuppressWarnings("unused")
	private static byte[] bigSize = new byte[2 * _1MB];
	
	public static void testGC() {
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA.instance = objB;
		objB.instance = objA;
		objA = null;
		objB = null;
		
		System.gc();
	}
	
	public static void main(String[] args) {
		testGC();
	}
	
}
