package com.example.cb;
import org.apache.commons.beanutils.PropertyUtils;

public class CBMethods {
    public static void main(String[] args) throws Exception{
        System.out.println(PropertyUtils.getProperty(new javaBeanTest(),"name"));
    }
}
