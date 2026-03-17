package com.example;

public class Person {
    //这里的Person没有实现Serializable接口
    public String name;
    public transient int age;	//这里的age用transient修饰符
    private float weight;
    public Person(String name,int age,float weight){
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}