package com.xin.lambda;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.xin.MathOperation;
import com.xin.Person;

import java.util.Arrays;
import java.util.List;

/**
 * @author Three
 * @since 18/6/9上午8:58
 */
public class LambdaTest {

    public static void main(String[] args) {

        System.out.println("lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。");
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;


        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            a += b;
            return a * b;
        };


        System.out.println(addition.operation(1, 2));
        System.out.println(subtraction.operation(4, 2));
        System.out.println(multiplication.operation(3, 2));


        int a = 0;
        new Thread(
                () -> System.out.println(a)
        ).start();

        List<String> aList = ImmutableList.of("1", "2", "3");
        aList.forEach(string -> System.out.println(string));
        aList.forEach(System.out::println);


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println("Print even numbers:");
        evaluate(ImmutableList.of(1, 2, 3, 4, 5), (n) -> n != null && n % 2 == 0);
//        evaluate(list, isDouble());
    }

    private static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.apply(n)) {
                System.out.print(n + " ");
            }
        }
    }

    private static Predicate<Integer> isDouble() {
        return p -> p != null && p % 2 == 2;
    }
}
