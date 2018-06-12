package com.xin.collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.xin.Person;

import static com.xin.Const.printLineSeparator;

/**
 * @author Three
 * @since 18/6/6下午1:54
 */
public class MultimapTest {
    public static void main(String[] args) {
        System.out.println("每个有经验的 Java 程序员都在某处实现过 Map<K, List>或 Map<K, Set>,并且要忍受这个结构的笨拙。");
        System.out.println("例 如,Map<K, Set>通常用来表示非标定有向图。Guava 的 Multimap 可以很容易地把一个键映射到多个值。");
        System.out.println("换 句话说,Multimap 是把键映射到任意多个值的一般方式。");
        System.out.println("可以用两种方式思考 Multimap 的概念:”键-单个值映射”的集合: a -> 1 a -> 2 a ->4 b -> 3 c -> 5");
        System.out.println("或者”键-值集合映射”的映射:a -> [1, 2, 4] b -> 3 c -> 5");
        printLineSeparator();

        System.out.println("很少会直接使用 Multimap 接口,更多时候你会用 ListMultimap 或 SetMultimap 接口,它们分别把键映射到 Li st 或 Set。");
        System.out.println("Multimap.get(key)以集合形式返回键􏰀对应的值视图,即使没有任何对应的值,也会返回空集合。ListMultima p.get(key)返回 List,SetMultimap.get(key)返回 Set。");
        printLineSeparator();

        Multimap<String, Person> personMultimap = ArrayListMultimap.create();
        personMultimap.put("A", new Person("A", 1));
        personMultimap.put("A", new Person("A", 1));
        personMultimap.put("A", new Person("B", 2));
        personMultimap.put("A", new Person("B", 3));
        personMultimap.put("B", new Person("D", 3));

        System.out.println(personMultimap.get("A"));
        System.out.println(personMultimap.entries());
        System.out.println(personMultimap.keys());//可以从这个Multiset 中移除元素,但不能做添加操作

//        personMultimap.asMap().put("C", Lists.newArrayList(new Person("C", 2)));异常，不支持的操作
        System.out.println(personMultimap.entries());
        System.out.println(personMultimap.asMap().entrySet());
        printLineSeparator();

        System.out.println("asMap为 Multimap<K, V>提供 Map<K,Collection>形式的视图。");
        System.out.println("返回的 Map 支持 remove 操作,并且 会反映到底层的 Multimap,但它不支持 put 或 putAll 操作。");
        System.out.println("更重要的是,如果你想为 Multimap 中没有的 键返回 null,而不是一个新的、可写的空集合,你就可以使用 asMap().get(key)。");
        System.out.println("(你可以并且应当把 as Map.get(key)返回的结果转化为适当的集合类型——如 SetMultimap.asMap.get(key)的结果转为 Set,ListMultimap.asMap.get(key)的结果转为 List——Java 类型系统不允许 ListMultimap 直接为 asMap.get(key)返回 List");

        printLineSeparator();
        System.out.println("*LinkedListMultimap.entries()保留了􏰀有键和值的迭代顺序。");
        System.out.println("*LinkedHashMultimap 保留了映射项的插入顺序,包括键插入的顺序,以及键映射的􏰀有值的插入顺序。");
    }


}

