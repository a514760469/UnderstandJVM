package com.jvm.classloader;

import java.lang.reflect.Method;

/**
 * JavaClass执行工具
 * @author zhanglifeng
 */
public class JavaClassExecutor {

	/**
	 * 执行外部传过来的代表一个Java类的byte数组<br>
	 * 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info 常量修改为劫持后的HackSystem类
	 * 执行方法为该类的main 方法, 输出结果为该类向System.out/err输出的信息
	 * @param classByte 
	 * @return 执行结果
	 */
	public static String execute(byte[] classByte) {
		HackSystem.clearBuffer();
		ClassModifier cm = new ClassModifier(classByte);
		byte[] modiByte = cm.modifyUTF8Constant("java/lang/System", "com/jvm/classloader/HackSystem");
		HotSwapClassLoader loader = new HotSwapClassLoader();
		Class<?> clazz = loader.loadByte(modiByte);
		
		try {
			Method method = clazz.getMethod("main", new Class[]{String[].class});
			method.invoke(null, new Object[]{null});
		} catch (Exception e) {
			e.printStackTrace(HackSystem.out);	
		}
		return HackSystem.getBufferString();
	}
	
}
