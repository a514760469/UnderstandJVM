package com.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {
	
	public static void main(String[] args) {
		IntStream.range(1, 5).forEach(System.out::println);
		LongStream.range(0, 5).forEach(System.out::println);
	}

	/**
	 * 
	 */
	@Test
	public void test1() {
		Arrays.stream(new int[]{1, 2, 3, 4})
		.map(n -> 2 * n + 1)
		.average()
		.ifPresent(System.out::println);
	}
	
	@Test
	public void test2() {
		Stream.of("a1", "a2", "a3")
		.map(s -> s.substring(1))
		.mapToInt(Integer::parseInt)
		.max().ifPresent(System.out::println);
	}
	
	@Test
	public void test3() {
		IntStream.range(1, 4)
		.mapToObj(i -> "a" + i)
		.forEach(System.out::println);
	}
	
	@Test
	public void test4() {
		Stream.of("a1", "a2", "a3", "d2", "b1", "b3", "c")
		.filter(s -> {
			System.out.println("filter: " + s);
			return true;
		}).forEach(s -> System.out.println("forEach: " + s) );
	}
	
	@Test
	public void test5() {
		boolean match = Stream.of("d2", "b1", "a2", "b3", "c")
			.map(s -> {
				System.out.println("map: " + s);
				return s.toUpperCase();
			})
			.anyMatch(s -> {
				System.out.println("anyMatch: " + s);
				return s.startsWith("A");
			});
		System.out.println("match: " + match);
		
	}
	
	@Test
	public void test6() {
		Stream.of("d2", "a2", "b1", "b3", "c")
				.filter(s -> {
					System.out.println("filter: " + s);
					return s.startsWith("a");
				})
				.sorted((s1, s2) -> {
					System.out.printf("sorted: %s; %s\n", s1, s2);
					System.out.println(s1.compareTo(s2));
					return s1.compareTo(s2);
				})
				.map(s -> {
					System.out.println("map: " + s);
					return s.toUpperCase();
				})
				.forEach(s -> System.out.println("forEach: " + s));
	}

	/**
	 * 生成10个随机数
	 */
	@Test
	public void testStreamGenerate() {

		Stream.generate(new Random()::nextInt).limit(10).forEach(System.out::println);
		// IntStream 逼格很高
		IntStream.generate(() -> (int) System.nanoTime() % 100).limit(10).forEach(System.err::println);
		// 等差数列
		Stream.iterate(0, n -> n + 3).limit(10).forEach(System.err::println);
	}

}


