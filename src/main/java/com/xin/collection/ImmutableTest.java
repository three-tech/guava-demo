package com.xin.collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * @author Three
 * @since 18/6/6上午11:46
 */
public class ImmutableTest {

    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of("red",
            "orange", "yellow", "green", "blue", "purple");
    public static final ImmutableMap<String, Integer> immutableMap = ImmutableMap.of("a", 1, "b", 2);

    public static void main(String[] args) {

        System.out.println("不可变对象有很多优点,包括:");
        System.out.println("当对象被不可信的库调用时,不可变形式是安全的;");
        System.out.println("不可变对象被多个线程调用时,不存在竞态条件问题");
        System.out.println("不可变集合不需要考虑变化,因此可以节省时间和空间。􏰀有不可变的集合都比它们的可变形式有更好的内存利用率(分析和测试细节);");
        System.out.println("不可变对象因为有固定不变,可以作为常量来安全使用。 创建对象的不可变拷贝是一项很好的防御性编程技巧。");

        System.out.println("********copyOf********");
        ImmutableSet<String> colorCopy = ImmutableSet.copyOf(COLOR_NAMES);
        System.out.println(colorCopy);
        ImmutableList<String> defensiveCopy = ImmutableList.copyOf(colorCopy);
        System.out.println(defensiveCopy);

        System.out.println("********asList********");

        System.out.println("所有不可变集合都有一个 asList()方法提供 ImmutableList 视图,来帮助你用列表形式方便地读取集合元素。");
        System.out.println("例如,你可以使用 sortedSet.asList().get(k)从 ImmutableSortedSet 中读取第 k 个最小元素。");
    }

}
