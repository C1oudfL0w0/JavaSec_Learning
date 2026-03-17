package com.example;
import java.sql.*;

public class Client {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://127.0.0.1:3309/mysql?characterEncoding=utf8&useSSL=false&queryInterceptors=com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor&autoDeserialize=true"; //8.x使用
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(DB_URL);
    }
}