package com.example;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Module_JdkAopProxy {
    public static Object getBProxy(Object obj, Class[] clazzs) throws Exception {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTarget(obj);
        Constructor<?> constructor = Class.forName("org.springframework.aop.framework.JdkDynamicAopProxy").getConstructor(new Class[] { AdvisedSupport.class });
        constructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler)constructor.newInstance(new Object[] { advisedSupport });
        Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), clazzs, handler);
        return proxy;
    }

    public static Object getAProxy(Object obj, Class<?> clazz) throws Exception {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTarget(obj);
        AbstractAspectJAdvice advice = (AbstractAspectJAdvice)obj;
        DefaultIntroductionAdvisor advisor = new DefaultIntroductionAdvisor((Advice)getBProxy(advice, new Class[] { MethodInterceptor.class, Advice.class }));
        advisedSupport.addAdvisor((Advisor)advisor);
        Constructor<?> constructor = Class.forName("org.springframework.aop.framework.JdkDynamicAopProxy").getConstructor(new Class[] { AdvisedSupport.class });
        constructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler)constructor.newInstance(new Object[] { advisedSupport });
        Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { clazz }, handler);
        return proxy;
    }
}
