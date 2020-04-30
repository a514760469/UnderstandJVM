package com.nio.regexp;

import java.nio.CharBuffer;

/**
 * @author zhanglifeng
 * @since 2020-04-28 16:14
 */
public class CharSeq {

    public static void main(String[] args) {

        StringBuffer stringBuffer = new StringBuffer("Hello World");

        CharBuffer charBuffer = CharBuffer.allocate(20);

        // 直接来源于String
        CharSequence charSequence = "Hello World";
        printCharSequence(charSequence);

        charSequence = stringBuffer;
        printCharSequence(charSequence);

        stringBuffer.setLength(0);
        stringBuffer.append("Goodbye cruel world");
        printCharSequence(charSequence);

        charSequence = charBuffer;
        charBuffer.put("xxxxxxxxxxxxxxxxxxx");// 20 chars : xxxxxxxxxxxxxxxxxxxx
        charBuffer.clear();

        charBuffer.put("Hello World");
        charBuffer.flip();
        printCharSequence(charSequence);

        charBuffer.mark();
        charBuffer.put("Seeya");
        charBuffer.reset();
        printCharSequence(charSequence);

        charBuffer.clear();
        printCharSequence(charSequence);

    }

    private static void printCharSequence(CharSequence cs) {
        System.out.println("length = " + cs.length() + ", content = '" + cs.toString() + "'");
    }

}
