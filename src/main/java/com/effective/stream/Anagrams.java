package com.effective.stream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * 扫描一个文件换位词
 */
public class Anagrams {

    public static void main(String[] args) throws IOException {
        anagramStreamVersion(args);
        File dictionary = new File(args[0]);

        int minGroupSize = Integer.parseInt(args[1]);
        Map<String, Set<String>> groups = new HashMap<>();
        try (Scanner s = new Scanner(dictionary)) {
            while (s.hasNext()) {
                String word = s.next();
                groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>()).add(word);
            }
        }
        for (Set<String> group : groups.values()) {
            if (group.size() >= minGroupSize) {
                System.out.println(group.size() + ": " + group);
            }
        }
//        "hello world!".chars().forEach(x -> System.out.print((char) x));
    }

    /**
     * 按字母顺序排列
     * @return
     */
    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }

    /**
     * anagram stream版本的实现
     * @param args
     */
    public static void anagramStreamVersion(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        try (Stream<String> words = Files.lines(dictionary)) {
            words.distinct().collect(groupingBy(Anagrams::alphabetize))
                .values().stream()
                .filter(group -> group.size() >= minGroupSize)
                .forEach(g -> System.out.println(g.size() + ": " + g));
        }
    }
}
