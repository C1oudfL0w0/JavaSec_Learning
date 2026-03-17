package com.example;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.TreeMap;

public class basic {
    public static String ser(Object object) throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);
        hessian2Output.getSerializerFactory().setAllowNonSerializable(true);
        hessian2Output.writeObject(object);
        hessian2Output.flushBuffer();
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
    public static Object unser(String string) throws Exception{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(string));
        Hessian2Input hessian2Input = new Hessian2Input(byteArrayInputStream);
        return hessian2Input.readObject();
    }

    public static void main(String[] args) throws Exception {
        String s = ser(new Person("0w0",21,70));
        Person person = (Person) unser(s);
        System.out.println(person);

//        HashMap hashMap = new HashMap();
//        hashMap.put(new Object(),null);
//        String hashMapStr = ser(hashMap);
//        unser(hashMapStr);

//        TreeMap<Object,Object> treeMap = new TreeMap<>();
//        treeMap.put(new Comparable() {
//            @Override
//            public int compareTo(Object o) {
//                return 0;
//            }
//        }, null);
//
//        String treeMapStr = ser(treeMap);
//        unser(treeMapStr);
    }
}
