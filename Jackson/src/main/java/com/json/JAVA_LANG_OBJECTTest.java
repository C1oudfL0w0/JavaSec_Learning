package com.json;

import com.example.Hacker;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JAVA_LANG_OBJECTTest {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        p.age = 6;
        p.name = "0w0";
        p.object = new Hacker();
        ObjectMapper mapper = new ObjectMapper();
        // 设置JAVA_LANG_OBJECT  
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT);

        String json = mapper.writeValueAsString(p);
        System.out.println(json);

        Person p2 = mapper.readValue(json, Person.class);
        System.out.println(p2);
    }
}