package com.xin.string;

import com.google.common.base.*;

import static com.xin.Const.printLineSeparator;
import static com.xin.Const.printSeparator;

/**
 * @author Three
 * @since 18/6/9上午8:27
 */
public class StringTest {
    public static void main(String[] args) {
        printSeparator("joiner");
        joiner();
        printLineSeparator();


        printSeparator("splitter");
        splitter();
        printLineSeparator();

        printSeparator("charMatcher");
        charMatcher();
        printLineSeparator();

        printSeparator("charSets");
        charSet();
    }

    private static void joiner() {
        System.out.println("joiner 实例总是不可变的。用来定义 joiner 目标语义的配置方法总会返回一个新的 joiner 实例。");
        System.out.println("这使得 joiner 实例都是线程安全的,你可以将其定义为 static final常量");
        Joiner joiner = Joiner.on("; ").skipNulls();
        System.out.println(joiner.join("one", "two", null, "three", null));

    }

    private static void splitter() {
        System.out.println(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("  foo,bar,, qux"));

        System.out.println(Splitter.fixedLength(2)
                .trimResults(CharMatcher.digit())
                .omitEmptyStrings()
                .split("11u3io12oyi31oi3io1io23123"));

    }

    private static void charMatcher() {
        System.out.println(CharMatcher.digit().retainFrom("123Test32~!@#$%^&*"));//只保留数字
        System.out.println(CharMatcher.javaDigit().replaceFrom("123Test123", "*"));//用*替换数字
        System.out.println(CharMatcher.digit().replaceFrom("123Test123", "*"));//用*替换数字

        System.out.println(CharMatcher.digit().or(CharMatcher.javaLowerCase()).retainFrom("123Ret$%^&*"));//只保留数字和小写字母

        System.out.println(CharMatcher.anyOf("123").retainFrom("123Test13"));//匹配anyOf

    }

    private static void charSet() {
        System.out.println(Charsets.UTF_8);
        System.out.println(Charsets.ISO_8859_1);
        System.out.println(Charsets.US_ASCII);
        System.out.println(Charsets.UTF_16);
    }
}
