package com.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserializationRun {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();

        String json = "{\"age\":16,\"name\":\"0w0\",\"sex\":[\"com.json.MySex\",{\"sex\":1}]}";
        Person3 p2 = mapper.readValue(json, Person3.class);
        System.out.println(p2);

    }
}