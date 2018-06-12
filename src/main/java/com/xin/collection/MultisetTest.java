package com.xin.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.xin.Const;

import static com.xin.Const.printLineSeparator;

/**
 * @author Three
 * @since 18/6/6上午11:58
 */
public class MultisetTest {

    public static void main(String[] args) {

        System.out.println("统计一个词在文档中出现了多少次,传统的做法是这样的:");
        System.out.println("Map<String, Integer> counts = new HashMap<String, Integer>(); ");
        System.out.println("for (String word : words) {");
        System.out.println("    Integer count = counts.get(word); if (count == null) {");
        System.out.println("        counts.put(word, 1); } else {");
        System.out.println("        counts.put(word, count + 1); }");
        System.out.println("}");
        System.out.println("这种写法很笨拙,也容易出错,并且不支持同时收集多种统计信息,如总词数");
        Const.printSeparator("Multiset");
        System.out.println("Multiset继承自 JDK 中的 Collection 接口,而不是 Set 接口");

        System.out.println("count(E) 给定元素在 Multiset 中的计数");
        System.out.println("elementSet() Multiset 中不重复元素的集合,类型为 Set<E>");
        System.out.println("entrySet() 和 Map 的 entrySet 类似,返回 Set<Multiset.Entry<E>>,其中包含的 Entry 支 持 getElement()和 getCount()方法");
        System.out.println("add(E, int)增加给定元素在 Multiset 中的计数");
        System.out.println("remove(E, int)减少给定元素在 Multiset 中的计数");
        System.out.println("setCount(E, int)设置给定元素在 Multiset 中的计数,不可以为负数");
        System.out.println("size() 返回集合元素的总个数(包括重复的元素)");
        printLineSeparator();
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("A");
        multiset.add("A");
        multiset.add("A");
        multiset.add("B");
        multiset.add("B");
        multiset.add("C");
        System.out.println(multiset.count("A"));
        System.out.println(multiset.count("D"));
        System.out.println(multiset.elementSet());
        multiset.setCount("D", 3);
        System.out.println(multiset.entrySet());


        printLineSeparator();

        System.out.println("请注意,Multiset不是 Map<E, Integer>,虽然 Map 可能是某些 Multiset 实现的一部分。准确来说 Multiset 是一种 Collection 类型,并履行了 Collection 接口相关的契约。关于 Multiset 和 Map 的显著区别还包括:");
        System.out.println("• Multiset 中的元素计数只能是正数。任何元素的计数都不能为负,也不能是 0。elementSet()和 entrySe t()视图中也不会有这样的元素。");
        System.out.println("• multiset.size()返回集合的大小,等同于􏰀有元素计数的总和。对于不重复元素的个数,应使用 elementSe t().size()方法。(因此,add(E)把 multiset.size()增加 1)");
        System.out.println("• multiset.iterator()会迭代重复元素,因此迭代长度等于 multiset.size()。");
        System.out.println("• Multiset 支持直接增加、减少或设置元素的计数。setCount(elem, 0)等同于移除􏰀有 elem。");
        System.out.println("• 对 multiset 中没有的元素,multiset.count(elem)始终返回 0。");

    }
}
