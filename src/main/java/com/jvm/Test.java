package com.jvm;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) throws IOException {
//        int max = 200;
//        int fileCounts = 1000;
//        int batches = fileCounts % max == 0 ? fileCounts / max : (fileCounts / max) + 1;
//        System.out.println("batches = " + batches);

//        Stream<Path> files = Files.walk(Paths.get("/Users/zhanglifeng/Downloads/20220421/"));
//        List<Path> paths = files.collect(Collectors.toList());

//        System.out.println("paths = " + paths.stream());

        int a = Integer.MAX_VALUE;
        System.out.println(Integer.toBinaryString(a));
//        String s = new String();

        System.out.println(a + 1);
        System.out.println(Integer.toBinaryString(a + 1));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));

        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE + 1));
    }
}
