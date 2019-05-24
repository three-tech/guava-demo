package com.xin.lambda;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.xin.MathOperation;

/**
 * @Author:Three
 * @Description:
 * @since 2018/8/22 14:33
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        new Thread(
                () -> System.out.println("this is lambda")
        );

    }
}
