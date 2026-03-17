package com.example;
import java.sql.*;
public class demo {
    public static void main(String[] args) throws Exception {
        String Driver = "com.mysql.cj.jdbc.Driver"; // 从mysql-connector-java 6开始
        //String Driver = "com.mysql.jdbc.Driver"; // mysql-connector-java 5
        String DB_URL = "jdbc:mysql://127.0.0.1:3306/TestSQL?useSSL=false&serverTimezone=GMT%2B8";	// 注意高版本mysql服务需要指定useSSL参数，高版本jdbc jar包需要指定serverTimezone时区
        //1.加载启动
        Class.forName(Driver);
        //2.建立连接
        Connection conn = DriverManager.getConnection(DB_URL, "root", "root");
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users");
        //如果有数据，rs.next()返回true
        while (rs.next()) {
            System.out.println(rs.getString("id") + " : " + rs.getString("username"));
        }
    }
}