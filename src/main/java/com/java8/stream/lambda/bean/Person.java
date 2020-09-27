package com.java8.stream.lambda.bean;

import java.math.BigDecimal;

/**
 * @author zhanglifeng
 * @since 2019/10/26/0026
 */
public class Person {

    private String name;

    private int age;

    private int stature;

    private BigDecimal amount;

    public Person() {
    }

    public Person(String name, int age, int stature) {
        this.name = name;
        this.age = age;
        this.stature = stature;
    }

    /**
     * groupBy加金额
     */
    public Person(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStature() {
        return stature;
    }

    public void setStature(int stature) {
        this.stature = stature;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", stature=" + stature +
                '}';
    }
}
