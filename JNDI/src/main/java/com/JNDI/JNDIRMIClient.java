package com.JNDI;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDIRMIClient {
    public static void main(String[] args) throws NamingException {
        String uri = "rmi://127.0.0.1:7778/RCE";
        InitialContext initialContext = new InitialContext();
        initialContext.lookup(uri);
    }
}