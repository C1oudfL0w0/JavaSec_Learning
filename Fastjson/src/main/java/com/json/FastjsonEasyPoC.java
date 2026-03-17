package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

public class FastjsonEasyPoC {
    public static void main(String[] args){
        String jsonString ="{\"@type\":\"com.json.Student\",\"age\":16,\"name\":\"0w0\",\"address\":\"china\",\"properties\":{}}";
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);

        Object obj = JSON.parseObject(jsonString, Object.class);
//        Student obj = JSON.parseObject(jsonString, Student.class, Feature.SupportNonPublicField);
        System.out.println(obj);
        System.out.println(obj.getClass().getName());
    }
}