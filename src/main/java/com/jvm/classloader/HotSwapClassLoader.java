package com.jvm.classloader;

/**
 * 公开ClassLoader中的defineClass()方法。通过loadByte()方法调用  把byte[] 转变为Class对象
 * HotSwapClassLoader中没有重写loadClass() 或 findClass(),因此加载范围与父类一直，除非手动调用loadByte()
 * @author zhanglifeng
 */
public class HotSwapClassLoader extends ClassLoader {
	/**
	 * HotSwapClassLoader类的类加载器作为父类加载器
	 */
	public HotSwapClassLoader() {
		super(HotSwapClassLoader.class.getClassLoader());
	}

	public Class<?> loadByte(byte[] classByte) {
		return defineClass(null, classByte, 0, classByte.length);
	}
}
