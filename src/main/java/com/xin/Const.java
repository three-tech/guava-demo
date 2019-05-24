package com.xin;

/**
 * @author Three
 * @since 18/6/6下午1:44
 */
public class Const {
    private static String separator      = "********%s*******";
    private static String line_separator = "---------------------";

    public static void printSeparator(String key) {
        System.out.println(String.format(separator, key));
    }

    public static void printLineSeparator() {
        System.out.println(line_separator);
    }
}
