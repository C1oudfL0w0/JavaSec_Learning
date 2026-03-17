package com.json;

import com.example.Hacker;

public class Person {
    public int age;
    public String name;
    public Object object;
    public Sex sex;
    public Hacker hacker;

    @Override
    public String toString() {
        return String.format("com.json.Person.age=%d, com.json.Person.name=%s, %s, %s, %s", age, name, object == null ? "null" : object, sex == null ? "null" : sex, hacker == null ? "null" : hacker);
    }
}