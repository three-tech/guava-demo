package com.xin.function;

import lombok.Data;

/**
 * @author three
 * @since 2019/6/17 17:41
 * <p>
 *
 * </p>
 */
public class ConstructorReference {


	public static void main(String[] args) {
		PersonFactory<Person> factory = Person::new;
		factory.create("a", 12);
	}
}

interface PersonFactory<T extends Person> {
	T create(String name, int age);
}

@Data
class Person {
	private String name;
	private int    age;

	public Person() {
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
