package com.jvm.loadclass;

public class NotInitialization {
	/**
	 * -XX:+TraceClassLoading   打印类加载 
	 * -Xverify:none 		   	关闭大部分类验证
	 * @param args
	 */
	public static void main(String[] args) {
		System.err.println(SubClass.value);
//		SuperClass[] sca = new SuperClass[10];
//		System.out.println(sca);
		// 编译阶段优化，已将HELLOWORLD存储到NotInitialization的常量池中
		System.out.println(ConstClass.HELLOWORLD);
	}
}
