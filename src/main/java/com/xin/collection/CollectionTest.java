package com.xin.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.xin.Const.printLineSeparator;
import static com.xin.Const.printSeparator;

/**
 * @author Three
 * @since 18/6/8上午9:51
 */
public class CollectionTest {

    public static void main(String[] args) {
        printSeparator("iterable");
        Iterable<Integer> concatenated = Iterables.concat(
                Ints.asList(1, 2, 3),
                Ints.asList(4, 5, 6)); // concatenated包括元素 1, 2, 3, 4, 5, 6
        System.out.println(Iterables.toString(concatenated));

        printLineSeparator();
        printSeparator("lists");

        List       countUp   = Ints.asList(1, 2, 3, 4, 5);
        List       countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}
        List<List> parts     = Lists.partition(countUp, 2);//把 List 按指定大小分割  {{1,2}, {3,4}, {5}}

        System.out.println(countDown);
        System.out.println(parts);

        printLineSeparator();

        printSeparator("sets");
        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes               = ImmutableSet.of("two", "three", "five", "seven");
        System.out.println(Sets.union(wordsWithPrimeLength, primes));
        Sets.SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength);
        // intersection包含"two", "three", "seven"
        System.out.println(intersection.immutableCopy());


        Set<String> animals = ImmutableSet.of("gerbil", "hamster");
        Set<String> fruits  = ImmutableSet.of("apple", "orange", "banana");
        System.out.println(Sets.cartesianProduct(animals, fruits));//返回􏰀有集合的笛卡儿积
        Set<Set<String>> animalSets = Sets.powerSet(animals);//返回给定集合的􏰀有子集
        System.out.println(JSON.toJSONString(animalSets));

        printLineSeparator();

        printSeparator("maps");
        System.out.println("Maps.uniqueIndex(Iterable,Function) 通常针对的场景是:");
        System.out.println("有一组对象,它们在某个属性上分别有独一无二的值,而我们希望能够按照这个属性值查找对象");
        System.out.println("——译者注:这个方法返回一个 Map,键为Function 返回 的属性值,值为 Iterable 中相应的元素,因此我们可以反复用这个 Map 进行查找操作。");
        System.out.println("比方说,我们有一堆字符串,这些字符串的长度都是独一无二的,而我们希望能够按照特定长度查找字符串:");
        ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(ImmutableList.<String>of("1", "12", "123"),
                (string) -> string.length()
        );
        System.out.println(stringsByIndex.get(1));
        System.out.println(stringsByIndex.get(2));
        System.out.println(stringsByIndex.get(3));

        printLineSeparator();
        System.out.println("Maps.difference(Map, Map) 用来比较两个 Map 以获取􏰀有不同点。该方法返回 MapDifference 对 象,把不同点的维恩图分解为:");
        System.out.println("entriesInCommon()         两个Map中都有的映射项,包括匹配的键与值");
        System.out.println("entriesDiffering()￼       键相同但是值不同值映射项。返回的 Map 的值类型为 MapDifference.Valu eDifference,以表示左右两个不同的值");
        System.out.println("entriesOnlyOnLeft()       键只存在于左边 Map 的映射项");
        System.out.println("entriesOnlyOnRight()      键只存在于右边 Map 的映射项");

        Map<String, Integer>           left  = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer>           right = ImmutableMap.of("a", 2, "d", 2, "c", 3);
        MapDifference<String, Integer> diff  = Maps.difference(left, right);

        System.out.println(diff.entriesInCommon());
        System.out.println(diff.entriesDiffering());
        System.out.println(diff.entriesOnlyOnLeft());
        System.out.println(diff.entriesOnlyOnRight());
    }
}
