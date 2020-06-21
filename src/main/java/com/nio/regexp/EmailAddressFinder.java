package com.nio.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证电子邮件地址。
 * @author zhanglifeng
 * @since 2020-04-29 10:54
 */
public class EmailAddressFinder {

    /**
     * @param args
     * "Looking at Ron Hitchens,ron@ronsoft.com.,fred@bedrock.com,barney@rubble.org,Wilma<wflintstone@rockvegas.com>...ron@ronsoft.com fred@bedrock.com barney@rubble.org wflintstone@rockvegas.com"
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println ("usage: email address ...");
        }

        // 编译电子邮件地址检测器的模式
        Pattern pattern = Pattern.compile (
                "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]"
                + "{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))"
                + "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)", Pattern.MULTILINE);
        // 制作一个matcher对象
        Matcher matcher = pattern.matcher("");

        for (String arg : args) {
            boolean matched = false;
            System.out.println("");
            System.out.println("Looking at " + arg + " ...");

            // 将匹配器复位，查看当前实参字符串
            matcher.reset(arg);

            while (matcher.find()) {
                // 找到一个匹配
                System.out.println("\t" + matcher.group());
                matched = true;
            }
            if (!matched) {
                System.out.println("\tNo email addresses found");
            }
        }

    }

}
