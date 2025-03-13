package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class StudentUnserialize {
    public static void main(String[] args) {
        String jsonString = "{\"@type\":\"com.example.Student\",\"age\":16,\"name\":\"0w0\"}";
        Student student = JSON.parseObject(jsonString, Student.class, Feature.SupportNonPublicField);
        System.out.println(student);
        System.out.println(student.getClass().getName());
//        System.out.println(student.getName());
//        System.out.println(student.getAge());
    }
}