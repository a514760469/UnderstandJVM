package com.effective.stream;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamAdapter {

    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }

    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static void main(String[] args) {
        for (String s : iterableOf(Stream.of("a", "b", "c"))) {
            System.out.println(s);
        }
    }
}
