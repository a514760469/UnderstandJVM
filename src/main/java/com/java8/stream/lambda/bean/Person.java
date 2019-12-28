package com.java8.stream.lambda.bean;

/**
 * @author zhanglifeng
 * @date 2019/10/26/0026
 */
public class Person {

    private String name;

    private int age;

    private int stature;

    public Person() {
    }

    public Person(String name, int age, int stature) {
        this.name = name;
        this.age = age;
        this.stature = stature;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", stature=" + stature +
                '}';
    }
}
