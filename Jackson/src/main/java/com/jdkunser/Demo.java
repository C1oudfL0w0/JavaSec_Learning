package com.jdkunser;

import com.fasterxml.jackson.databind.node.POJONode;


public class Demo {
    public static void main(String[] args) throws Exception {
        User user = new User();
        POJONode jsonNodes = new POJONode(user);
        jsonNodes.toString();
    }
}