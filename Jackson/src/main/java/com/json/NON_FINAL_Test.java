package com.json;

import com.example.Hacker;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NON_FINAL_Test {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        p.age = 6;
        p.name = "0w0";
        p.object = new Hacker();
        p.sex = new MySex();
        p.hacker = new Hacker();
        ObjectMapper mapper = new ObjectMapper();
        // 设置NON_FINAL  
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        String json = mapper.writeValueAsString(p);
        System.out.println(json);

        Person p2 = mapper.readValue(json, Person.class);
        System.out.println(p2);
    }
}