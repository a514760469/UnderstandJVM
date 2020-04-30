package com.nio.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1) First argument is a regular expression
 * 第一个参数是一个正则表达式
 * 2) Second argument is a replacement string, optionally with capture group references ($1, $2, etc)
 * 第二个参数是一个替换字符串，可以选择使用捕获组引用($1、$2等)
 * 3) Any remaining arguments are treated as input strings to which the
 * 其余的参数将作为输入字符串处理
 * <p>
 * regular expression and replacement strings will be applied.
 * The effect of calling replaceFirst( ) and replaceAll( ) for each input string will be listed.
 * <p>
 * Be careful to quote the commandline arguments if they contain spaces or special characters.
 * <p>
 * 正则表达式替换
 *
 * @author zhanglifeng
 * @since 2020-04-29 16:42
 */
public class RegexReplace {

    /**
     * @param args ([bB])yte $1ite "Bytes is bytes"
     */
    public static void main(String[] args) {
        // 完整性检查，至少需要三个参数
        if (args.length < 3) {
            System.out.println("usage: regex replacement input ...");
            return;
        }
        // 用助词符号名保存正则及替换字符串
        String regex = args[0];
        String replace = args[1];
        // 编译表达式；一次只能编译一个
        Pattern pattern = Pattern.compile(regex);
        // 得到Matcher实例，暂时先使用虚设的输入字符串
        Matcher matcher = pattern.matcher("");
        // 打印输出用于参考
        System.out.println(" regex: '" + regex + "'");
        System.out.println(" replacement: '" + replace + "'");

        // 对各个剩余的参数字符串应用正则/替换
        for (int i = 2; i < args.length; i++) {
            System.out.println("---------------------------");
            matcher.reset(args[i]);
            System.out.println(" input: '" + args[i] + "'");
            System.out.println(" replaceFirst(): '" + matcher.replaceFirst(replace) + "'");
            System.out.println(" replaceAll(): '" + matcher.replaceAll(replace) + "'");
        }

    }
}
