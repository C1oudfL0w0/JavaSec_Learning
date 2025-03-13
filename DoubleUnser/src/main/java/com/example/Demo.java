package com.example;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;

public class Demo
{
    public static void main( String[] args ) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();
        SignedObject signedObject = new SignedObject(CC6EXP.getPayload(), kp.getPrivate(), Signature.getInstance("DSA"));
        signedObject.getObject();
    }
}
