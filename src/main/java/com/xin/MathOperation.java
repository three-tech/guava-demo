package com.xin;

/**
 * @author Three
 * @since 18/6/9上午8:58
 */
@FunctionalInterface
public interface MathOperation {
    int operation(int a, int b);

    default int addition(int a, int b) {
        return a + b;
    }
}
