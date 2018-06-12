package com.xin.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import static com.xin.Const.printLineSeparator;

/**
 * @author Three
 * @since 18/6/6下午2:19
 */
public class BiMapTest {

    public static void main(String[] args) {
        System.out.println("传统上,实现键值对的双向映射需要维护两个单独的 map,并保持它们间的同步。但这种方式很容易出错,而且 对于值已经在 map 中的情况,会变得非常混乱");
        printLineSeparator();
        System.out.println("BiMap<K, V>是特殊的 Map:");
        System.out.println("• 可以用 inverse()反转 BiMap<K, V>的键值映射");
        System.out.println("• 保证值是唯一的,因此 values()返回 Set 而不是普通的 Collection");
        System.out.println("在 BiMap 中,如果你想把键映射到已经存在的值,会抛出 IllegalArgumentException 异常。");
        System.out.println("如果对特定值,你想要强制替换它的键,请使用 BiMap.forcePut(key, value)。");
        printLineSeparator();

        BiMap<String, Integer> name_userId = HashBiMap.create();
        name_userId.put("A", 1);
//        name_userId.put("A", 2);//BiMap.forcePut(key, value)
        name_userId.put("B", 2);
        System.out.println(name_userId.entrySet());
        name_userId.forcePut("A", 3);
        System.out.println(name_userId.entrySet());

        System.out.println(name_userId.inverse().entrySet());


        printLineSeparator();
        System.out.println("键–值实现      值–键实现      对应的BiMap实现");
        System.out.println("HashMap       HashMap       HashBiMap");
        System.out.println("ImmutableMap  ImmutableMap  ImmutableBiMap");
        System.out.println("EnumMap       EnumMap       EnumBiMap");
        System.out.println("EnumMap       HashMap       EnumHashBiMap");

    }
}
