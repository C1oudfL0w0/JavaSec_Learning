package com.json;

public class MySex implements Sex {
    int sex;
    public MySex() {
        System.out.println("MySex构造函数");
    }
    @Override
    public int getSex() {
        System.out.println("com.json.MySex.getSex");
        return sex;
    }

    @Override
    public void setSex(int sex) {
        System.out.println("com.json.MySex.setSex");
        this.sex = sex;

        try {
            Runtime.getRuntime().exec("calc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}