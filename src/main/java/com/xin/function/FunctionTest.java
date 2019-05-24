package com.xin.function;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicates;
import com.google.common.collect.*;
import com.xin.Person;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.xin.Person.build;

/**
 * @author Three
 * @since 18/6/8上午11:05
 */
public class FunctionTest {
    public static void main(String[] args) {
        System.out.println("Guava 提供两个基本的函数式接口:");
        System.out.println("• Function<A, B>,它声明了单个方法 B apply(A input)。Function 对象通常被预期为引用透明的——没有 副作用——并且引用透明性中的”相等”语义与 equals 一致,如 a.equals(b)意味着 function.apply(a).eq uals(function.apply(b))。");
        System.out.println("• Predicate,它声明了单个方法 boolean apply(T input)。Predicate 对象通常也被预期为无副作用函 数,并且”相等”语义与 equals 一致。");

        System.out.println(Iterators.all(ImmutableList.of("1", "2").iterator(), Predicates.equalTo("1")));
        System.out.println(Maps.filterKeys(ImmutableMap.of("1", 1, "2", 2), Predicates.contains(Pattern.compile("1"))));
        System.out.println(Maps.transformValues(ImmutableMap.of("a", 1, "b", 2), transform()));

        List<String>        names          = Lists.newArrayList("a", "b");
        Map<String, Person> personWithName = ImmutableMap.of("a", build("a", 12), "b", build("b", 34));
        List<Person>        people         = Lists.transform(names, Functions.forMap(personWithName));
        System.out.println(people);

        ListMultimap<String, String> firstNameToLastNames = ArrayListMultimap.create();
        firstNameToLastNames.put("a", "aaa");
        firstNameToLastNames.put("b", "bbb");
        firstNameToLastNames.put("c", "ccc");
        // maps first names to all last names of people with that first name
        ListMultimap<String, String> firstNameToName = Multimaps.transformEntries(firstNameToLastNames,
                new Maps.EntryTransformer<String, String, String>() {
                    public String transformEntry(String firstName, String lastName) {
                        return firstName + " " + lastName;
                    }
                });
        System.out.println(firstNameToName);
    }

    private static Function transform() {
        return new Function<Integer, Object>() {
            public Object apply(Integer integer) {
                return integer * 2;
            }
        };

    }
}
