package com.xin.common;


import com.google.common.base.Optional;

/**
 * @author Three
 * @since 18/6/6上午10:54
 */
public class OptionalTest {
    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);//创建指定引用的 Optional 实例,若引用为 null 则快速失败

        System.out.println(possible.isPresent());//如果 Optional 包含非 null 的引用(引用存在),返回true
        System.out.println(possible.get());//￼返回 Optional 􏰀包含的引用,若引用缺失,则抛出 java.lang.IllegalStat Exception

        Optional<String> absent = Optional.absent();//创建引用缺失的 Optional 实例
        System.out.println(absent.isPresent());
        System.out.println(absent.or("test"));//返回 Optional 􏰀包含的引用,若引用缺失,返回指定的值


        Optional<String> fromNullable = Optional.fromNullable(null);//创建指定引用的 Optional 实例,若引用为 null 则表示缺失
        System.out.println(fromNullable.isPresent());
        System.out.println(fromNullable.orNull());//返回 Optional 􏰀包含的引用,若引用缺失,返回 null
    }
}
