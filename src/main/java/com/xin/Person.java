package com.xin;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Three
 * @since 18/6/6下午2:09
 */
@Data
@AllArgsConstructor
public class Person {
    private String name;
    private int    age;

    public static Person build(String name, int age) {
        return new Person(name, age);
    }
}