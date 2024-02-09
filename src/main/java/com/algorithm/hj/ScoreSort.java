package com.algorithm.hj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * HJ68 成绩排序
 * @author zhanglifeng
 * @since 2023-08-23
 */
public class ScoreSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        in.nextInt();
//        in.hasNext()

        List<Student> list = new ArrayList<>();
        list.add(new Student("jack", 70));
        list.add(new Student("peter", 96));
        list.add(new Student("Tom", 70));
        list.add(new Student("smith", 67));


        sort(list, 1);

        for (Student student : list) {
            System.out.println(student);
        }
    }

    public static void sort(List<Student> list, int flag) {
        if (flag == 0) {
            list.sort((o1, o2) -> {
                if (o1.score > o2.score) {
                    return 1;
                }
                else if (o1.score < o2.score) {
                    return -1;
                }
                return 0;
            });
        } else {
            list.sort((o1, o2) -> {
                if (o1.score > o2.score) {
                    return -1;
                }
                else if (o1.score < o2.score) {
                    return 1;
                }
                return 0;
            });
        }
    }



    public static class Student {

        public String name;
        public Integer score;

        public Student() {
        }

        public Student(String name, Integer score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return name + " " + score;
        }
    }
}
