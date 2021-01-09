package com.proxy.javassist;

import javassist.*;
import sun.misc.ProxyGenerator;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author zhanglifeng
 * @since 2021-01-05
 */
public class ProxyFactory {

    // javassist

    /**
     * 实例化这个对象
     * 创建一个类
     * 创建一个方法
     *
     * @throws NotFoundException 1
     */
    public static TestService createProxy() throws Exception {
        ClassPool classPool = new ClassPool();
        // classloader
        classPool.appendSystemPath();
        // 创建一个类：TestServiceImpl
        CtClass ctClass = classPool.makeClass("TestServiceImpl");
        ctClass.addInterface(classPool.get(TestService.class.getName()));
        // 这个类创建一个方法
        CtMethod me = CtNewMethod.make(CtClass.voidType, "sayHello", new CtClass[]{classPool.get(String.class.getName())},
                new CtClass[0], "{System.out.println(\"hello\" + $1);}", ctClass);
        ctClass.addMethod(me);

        // 转成java 里面的class
        Class<?> aClass = ctClass.toClass();
        return (TestService) aClass.newInstance();
    }

    public static <T> T createProxy2(Class<T> claInterface, String src) throws Exception {
        ClassPool classPool = new ClassPool();
        // classloader
        classPool.appendSystemPath();
        // 创建一个类：TestServiceImpl
        CtClass ctClass = classPool.makeClass("TestServiceImpl");
        ctClass.addInterface(classPool.get(claInterface.getName()));
        // 这个类创建一个方法
        CtMethod me = CtNewMethod.make(CtClass.voidType,
                "sayHello",
                new CtClass[]{ classPool.get(String.class.getName()) },
                new CtClass[0],
                src,
                ctClass);

        ctClass.addMethod(me);

        // 转成java 里面的class
        Class<?> aClass = ctClass.toClass();
        return (T) aClass.newInstance();
    }

    static int count = 0;
    /**
     * 1、支持所有的接口的代理
     * 2、按常规方式传递实现(代码)。
     */
    public static <T> T createProxy3(Class<T> cla, InvocationHandler handler) throws Exception {
        // 1、创建类
        ClassPool classPool = ClassPool.getDefault();
        CtClass impl = classPool.makeClass("$Proxy" + count++);
        impl.addInterface(classPool.makeClass(cla.getName()));
        // 2、添加属性handler
        CtField ctField = CtField.make("public com.proxy.asm.ProxyFactory.InvocationHandler handler = null;", impl);
        impl.addField(ctField);

        String src = "return ($r) this.handler.invoke(\"%s\", $args);";
        String voidSrc = "this.handler.invoke(\"%s\", $args);";


        // 2、创建接口下的所有方法
        for (Method method : cla.getMethods()) {
            CtClass returnType = classPool.get(method.getReturnType().getName());
            CtClass[] parameters = toCtClass(classPool, method.getParameterTypes());
            CtClass[] exceptions = toCtClass(classPool, method.getExceptionTypes());
            String methodName = method.getName();
            String srcImpl;
            if (method.getReturnType().equals(Void.class)) {
                srcImpl = voidSrc;
            } else {
                srcImpl = src;
            }
            CtMethod ctMethod = CtNewMethod.make(returnType, methodName, parameters, exceptions, String.format(srcImpl, methodName), impl);
            impl.addMethod(ctMethod);
        }
        Class<?> aClass = classPool.toClass(impl);

        byte[] bytes = impl.toBytecode();
        Files.write(Paths.get(System.getProperty("user.dir") + "/target/$Proxy.class"), bytes);
        Object o = aClass.newInstance();
        aClass.getField("handler").set(o, handler);
        return (T) o;
    }

    private static CtClass[] toCtClass(ClassPool pool, Class<?>[] classes) {

        return Arrays.stream(classes).map(c -> {
            try {
                return pool.get(c.getName());
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        }).toArray(CtClass[]::new);
    }


    public static void main(String[] args) throws Exception {
        TestService proxy3 = createProxy3(TestService.class, new InvocationHandler() {
            @Override
            public Object invoke(String methodName, Object[] args) {
                System.out.println("hello:" + args[0] + args[1]);
                return null;
            }
        });
        proxy3.sayHello2("1231", 14);
    }


    public interface InvocationHandler {
        Object invoke(String methodName, Object[] args);
    }

    public static class InvocationHandlerImpl implements InvocationHandler {

        @Override
        public Object invoke(String methodName, Object[] args) {
            System.out.println("handler invoke");
            return null;
        }
    }

    public interface TestService {

        void sayHello(String name);

        void sayHello2(String name, int i);

        String sayHello3(String name);
    }




    public static void jdkProxy() {
        ProxyGenerator.generateProxyClass("$proxy1.class", new Class[]{TestService.class});
    }
}
