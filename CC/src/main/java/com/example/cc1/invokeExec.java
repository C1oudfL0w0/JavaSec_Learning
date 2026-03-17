package com.example.cc1;

import org.apache.commons.collections.functors.InvokerTransformer;
//import java.lang.reflect.Method;

public class invokeExec {
    public static void main(String[] args) throws Exception {
        Runtime runtime = Runtime.getRuntime();
//        通过反射进行命令执行的方式
//        Class c = Runtime.class;
//        Method method = c.getDeclaredMethod("exec", String.class);
        InvokerTransformer invokerTransformer = new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"open -a calculator"});
        invokerTransformer.transform(runtime);
    }
}
