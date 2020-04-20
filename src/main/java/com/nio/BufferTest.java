package com.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.LongBuffer;

/**
 * @author zhanglifeng
 * @since 2020-04-17 14:43
 */
public class BufferTest {

    public static void main(String[] args) {
        CharBuffer buffer = CharBuffer.allocate(100);
        buffer.put('H').put('E').put('L').put('L').put('O');
        // 返回当前位置和限制之间的元素数。
        int length = buffer.remaining();

        char[] bigArray = new char[1000];
        buffer.get(bigArray, 0, length);

        System.out.println(bigArray);
        System.out.println(length);


        CharBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(20);
        LongBuffer longBuffer = byteBuffer.asLongBuffer();
//        byteBuffer.compact();
//        byteBuffer.equals()
//        byteBuffer.compareTo()
    }
}
