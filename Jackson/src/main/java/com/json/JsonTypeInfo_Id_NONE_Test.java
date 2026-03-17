package com.json;

import com.example.Hacker;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTypeInfo_Id_NONE_Test {
    public static void main(String[] args) throws Exception {
        User u = new User();
        u.username = "0w0";
        u.password = "******";
        u.object = new Hacker();
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(u);
        System.out.println(json);

        User u2 = mapper.readValue(json, User.class);
        System.out.println(u2);
    }
}