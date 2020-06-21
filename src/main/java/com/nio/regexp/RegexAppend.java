package com.nio.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式追加/替换
 *
 * @author zhanglifeng
 * @since 2020-04-30 11:37
 */
public class RegexAppend {

    public static void main(String[] args) {
        String input = "Thanks, thanks very much";
        String regex = "([Tt])hanks";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        // 循环直到遇到匹配
        while (matcher.find()) {
            if (matcher.group(1).equals("T")) {
                matcher.appendReplacement(sb, "Thank you");
            } else {
                matcher.appendReplacement(sb, "thank you");
            }
        }
        matcher.appendTail(sb);// 尾部追加到sb
        System.out.println(sb);

        // 2 使用 $n
        sb.setLength(0);
        matcher.reset();
        String replacement = "$1hank you";
        while (matcher.find()) {
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        System.out.println(sb);

        // 简单的方法
        System.out.println(matcher.replaceAll(replacement));
        // 字符串
        System.out.println(input.replaceAll(regex, replacement));
    }
}
