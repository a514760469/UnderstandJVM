package com.java8.stream.lambda;

import com.java8.stream.lambda.bean.Person;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhanglifeng
 * @date 2019/10/26/0026
 */
public class LambdaFunctionInterfaceTest {

    public static void main(String[] args) {

        Person person = new Person("DWI啊", 23, 175);

        Predicate<Integer> predicate = x -> x > 185;
        // Predicate    test(T t)    boolean   判断真假
        System.out.println(predicate.test(person.getStature()));
        // Consumer    accept(T t)  void      消费消息
        Consumer<String> consumer = System.out::println;
        consumer.accept("消费消息，消费消息，消费消息，***");

        // Function    apply(T)     R     将T映射为R
        Function<Person, String> function = Person::getName;
        function.apply(person);

        // Supplier     get()       T     生产消息
        Supplier<Integer> supplier = () -> Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

        // UnaryOperator  apply(T t)  R    一元操作(逻辑非)
        UnaryOperator<Boolean> unaryOperator = ugly -> !ugly;
        Boolean apply = unaryOperator.apply(true);
        System.out.println(apply);

        // BinaryOperator apply(T t, U u)  R  二元操作(乘积)
        BinaryOperator<Integer> binaryOperator = (x, y) -> x * y;
        Integer binApply = binaryOperator.apply(2, 3);
        System.out.println(binApply);

        test(() -> "我是一个演示的函数式接口");

        // 初始值为0的累加器
        System.out.println(Stream.of(1, 2, 3, 4).reduce(0, Integer::sum));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(10, binaryOperator));


        // 字符串拼接
        List<Person> students = new ArrayList<>(3);
        students.add(new Person("路飞", 22, 175));
        students.add(new Person("红发", 40, 180));
        students.add(new Person("白胡子", 50, 185));
        String joining = students.stream().map(Person::getName).collect(Collectors.joining(",", "[", "]"));
        System.out.println("字符串拼接: " + joining);

        // 分组
        Map<Boolean, List<Person>> groupMap = students.stream().collect(Collectors.groupingBy(o -> o.getAge() > 30));
        System.out.println(groupMap);

        // 平均
        Double avg = students.stream().collect(Collectors.averagingInt(Person::getAge));
        System.out.println(avg);
    }


    /**
     * 自定义函数接口测试
     */
    public static void test(Worker worker) {
        String work = worker.work();
        System.out.println(work);
    }

    // 注解可以省略
//    @FunctionalInterface
    public interface Worker {
        String work();
    }
}

