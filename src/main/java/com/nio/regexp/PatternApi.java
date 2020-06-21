package com.nio.regexp;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhanglifeng
 * @since 2020-04-28 17:35
 */
public class PatternApi {

    public static void main(String[] args) {
        Pattern compile = Pattern.compile("[A-Z][a-zA-Z]*", Pattern.CASE_INSENSITIVE | Pattern.UNIX_LINES);
//        compile.split(, )
        System.out.println(new PatternApi().goodAnswer("yes"));

        Matcher matcher = compile.matcher("sdas");
        matcher.reset();
    }

    public boolean goodAnswer(String answer) {
        return Pattern.matches("[Yy]es|[Yy]|[Tt]rue", answer);
    }

    public boolean test(String test) {
        Pattern pattern = Pattern.compile("[A-Z][a-zA-Z]*", Pattern.CASE_INSENSITIVE | Pattern.UNIX_LINES);
        return pattern.matcher(test).matches();
    }

    /**
     * 提取匹配的子序列
     */
    public CharSequence matchedSubSequence(Matcher matcher, CharSequence input) {
        CharSequence subSequence = null;
        if (matcher.find()) {
            subSequence = input.subSequence(matcher.start(), matcher.end());
        }
        return subSequence;
    }

    @Test
    public void matcherAppendReplacement() {

    }
}
