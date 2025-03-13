package com.example;

import org.apache.commons.collections.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.HashMap;

public class ois {
    public static Object unserialize(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename));
        Object obj=ois.readObject();
        return obj;
    }
    public static <serialize> void main(String[] args) throws IOException, ClassNotFoundException {
        serialize unser =(serialize)unserialize("1.bin");
        System.out.println(unser);
    }
}
