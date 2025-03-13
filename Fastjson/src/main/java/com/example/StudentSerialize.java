package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class StudentSerialize {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("0w0");
//        student.setAge(16);
//        String jsonString = JSON.toJSONString(student);
        String jsonString = JSON.toJSONString(student, SerializerFeature.WriteClassName);
        System.out.println(jsonString);
    }
}