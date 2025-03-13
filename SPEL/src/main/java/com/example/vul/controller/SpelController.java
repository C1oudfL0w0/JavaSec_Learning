package com.example.vul.controller;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpelController {

    @RequestMapping("/spel")
    @ResponseBody
    public String spelvul(String payload){
        //String cmdStr = "T(java.lang.ClassLoader).getSystemClassLoader().loadClass('java.lang.Runtime').getRuntime().exec('calc')";
        ExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration());//创建解析器
        Expression exp = parser.parseExpression(payload);//解析表达式
        return (String) exp.getValue();
    }
}